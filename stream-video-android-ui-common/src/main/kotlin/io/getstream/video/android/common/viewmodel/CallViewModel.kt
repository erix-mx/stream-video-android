/*
 * Copyright (c) 2014-2023 Stream.io Inc. All rights reserved.
 *
 * Licensed under the Stream License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://github.com/GetStream/stream-video-android/blob/main/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.getstream.video.android.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.getstream.log.taggedLogger
import io.getstream.result.Error
import io.getstream.video.android.common.permission.PermissionManager
import io.getstream.video.android.common.util.asStateFlowWhileSubscribed
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.DeviceStatus
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.call.RtcSession
import io.getstream.video.android.core.call.state.CallAction
import io.getstream.video.android.core.call.state.CallDeviceState
import io.getstream.video.android.core.call.state.FlipCamera
import io.getstream.video.android.core.call.state.LeaveCall
import io.getstream.video.android.core.call.state.ShowCallParticipantInfo
import io.getstream.video.android.core.call.state.ToggleCamera
import io.getstream.video.android.core.call.state.ToggleMicrophone
import io.getstream.video.android.core.call.state.ToggleSpeakerphone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.openapitools.client.models.CallSettingsResponse

/**
 * The CallViewModel is a light wrapper over
 *
 * - call
 * - call.state
 * - call.camera/microphone/speaker
 *
 * It's main purpose is to
 *
 * - Add awareness to the UI lifecycle. It makes sure we cleanup video state etc when you leave a call
 * - Helpers for picture in picture
 * - Helpers for fullscreen mode
 *
 * It also handles some UI state
 *
 * - Opening/closing the participant menu
 *
 */
public open class CallViewModel(public val call: Call) : ViewModel() {

    private val logger by taggedLogger("Call:ViewModel")

    // shortcut to the call settings
    private val settings: StateFlow<CallSettingsResponse?> = call.state.settings

    public val client: StreamVideo by lazy { StreamVideo.instance() }

    /** if we are in picture in picture mode */
    private val _isInPictureInPicture: MutableStateFlow<Boolean> = MutableStateFlow(false)
    public val isInPictureInPicture: StateFlow<Boolean> = _isInPictureInPicture

    private val _isShowingCallInfoMenu = MutableStateFlow(false)
    public val isShowingCallInfoMenu: StateFlow<Boolean> = _isShowingCallInfoMenu

    private var permissionManager: PermissionManager? = null

    private val isVideoOn: StateFlow<Boolean> =
        combine(settings, call.camera.status) { settings, status ->
            (settings?.video?.enabled == true) &&
                (status is DeviceStatus.Enabled) &&
                (permissionManager?.hasCameraPermission?.value == true)
        }.asStateFlowWhileSubscribed(scope = viewModelScope, initialValue = false)

    private val isMicrophoneOn: StateFlow<Boolean> =
        combine(settings, call.microphone.status) { _, status ->
            (status is DeviceStatus.Enabled) &&
                permissionManager?.hasRecordAudioPermission?.value == true
        }.asStateFlowWhileSubscribed(scope = viewModelScope, initialValue = false)

    private val isSpeakerphoneOn: StateFlow<Boolean> = call.speaker.status.map { status ->
        status is DeviceStatus.Enabled
    }.asStateFlowWhileSubscribed(scope = viewModelScope, initialValue = false)

    public val callDeviceState: StateFlow<CallDeviceState> =
        combine(
            isMicrophoneOn,
            isVideoOn,
            isSpeakerphoneOn
        ) { isAudioOn, isVideoOn, isSpeakerPhoneOn ->
            CallDeviceState(
                isMicrophoneEnabled = isAudioOn,
                isSpeakerphoneEnabled = isSpeakerPhoneOn,
                isCameraEnabled = isVideoOn
            )
        }.onEach {
            logger.d { "[callMediaState] callMediaState: $it" }
        }.asStateFlowWhileSubscribed(scope = viewModelScope, initialValue = CallDeviceState())

    private var onLeaveCall: (() -> Unit)? = null

    public fun joinCall(
        onSuccess: (RtcSession) -> Unit = {},
        onFailure: (Error) -> Unit = {}
    ) {
        viewModelScope.launch {
            withTimeout(CONNECT_TIMEOUT) {
                val result = call.join()
                result.onSuccess {
                    onSuccess.invoke(it)
                }.onError {
                    onFailure.invoke(it)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        dismissCallInfoMenu()
        // properly clean up
        call.leave()
    }

    public fun onCallAction(callAction: CallAction) {
        when (callAction) {
            is ToggleCamera -> onVideoChanged(callAction.isEnabled)
            is ToggleMicrophone -> onMicrophoneChanged(callAction.isEnabled)
            is ToggleSpeakerphone -> onSpeakerphoneChanged(callAction.isEnabled)
            is FlipCamera -> call.camera.flip()
            is LeaveCall -> onLeaveCall()
            is ShowCallParticipantInfo -> _isShowingCallInfoMenu.value = true

            else -> Unit
        }
    }

    private fun onVideoChanged(videoEnabled: Boolean) {
        logger.d { "[onVideoChanged] videoEnabled: $videoEnabled" }
        if (permissionManager?.hasCameraPermission?.value == false) {
            permissionManager?.requestPermission(android.Manifest.permission.CAMERA)
            logger.d { "[onVideoChanged] the [Manifest.permissions.CAMERA] has to be granted for video to be sent" }
        }

        call.camera.setEnabled(videoEnabled)
    }

    private fun onMicrophoneChanged(microphoneEnabled: Boolean) {
        logger.d { "[onMicrophoneChanged] microphoneEnabled: $microphoneEnabled" }
        if (permissionManager?.hasRecordAudioPermission?.value == false) {
            permissionManager?.requestPermission(android.Manifest.permission.RECORD_AUDIO)
            logger.d { "[onMicrophoneChanged] the [Manifest.permissions.RECORD_AUDIO] has to be granted for audio to be sent" }
        }
        call.microphone.setEnabled(microphoneEnabled)
    }

    private fun onSpeakerphoneChanged(speakerPhoneEnabled: Boolean) {
        logger.d { "[onSpeakerphoneChanged] speakerPhoneEnabled: $speakerPhoneEnabled" }
        call.speaker.setEnabled(speakerPhoneEnabled)
    }

    public fun setPermissionManager(permissionManager: PermissionManager?) {
        this.permissionManager = permissionManager
    }

    private fun onLeaveCall() {
        call.leave()
        onLeaveCall?.invoke()
    }

    public fun setOnLeaveCall(onLeaveCall: () -> Unit) {
        this.onLeaveCall = onLeaveCall
    }

    public fun dismissCallInfoMenu() {
        this._isShowingCallInfoMenu.value = false
    }

    public fun onPictureInPictureModeChanged(inPictureInPictureMode: Boolean) {
        this._isInPictureInPicture.value = inPictureInPictureMode
    }
}

private const val CONNECT_TIMEOUT = 30_000L