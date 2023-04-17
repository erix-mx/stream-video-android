/*
 * Copyright (c) 2014-2022 Stream.io Inc. All rights reserved.
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

package io.getstream.video.android.compose.ui.components.call.controls

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.getstream.video.android.compose.state.ui.call.CallControlAction
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.core.call.state.CallDeviceState
import io.getstream.video.android.core.call.state.FlipCamera
import io.getstream.video.android.core.call.state.LeaveCall
import io.getstream.video.android.core.call.state.ToggleCamera
import io.getstream.video.android.core.call.state.ToggleMicrophone
import io.getstream.video.android.core.call.state.ToggleSpeakerphone
import io.getstream.video.android.ui.common.R

/**
 * Builds the default set of Call Control actions based on the [callDeviceState].
 *
 * @param callDeviceState Information of whether microphone, speaker and camera are on or off.
 * @return [List] of [CallControlAction]s that the user can trigger.
 */
@Composable
public fun buildDefaultCallControlActions(
    callDeviceState: CallDeviceState
): List<CallControlAction> {
    val speakerphoneIcon =
        painterResource(
            id = if (callDeviceState.isSpeakerphoneEnabled) {
                R.drawable.stream_video_ic_speaker_on
            } else {
                R.drawable.stream_video_ic_speaker_off
            }
        )

    val microphoneIcon =
        painterResource(
            id = if (callDeviceState.isMicrophoneEnabled) {
                R.drawable.stream_video_ic_mic_on
            } else {
                R.drawable.stream_video_ic_mic_off
            }
        )

    val cameraIcon = painterResource(
        id = if (callDeviceState.isCameraEnabled) {
            R.drawable.stream_video_ic_videocam_on
        } else {
            R.drawable.stream_video_ic_videocam_off
        }
    )

    return listOf(
        CallControlAction(
            actionBackgroundTint = Color.White,
            icon = speakerphoneIcon,
            iconTint = Color.DarkGray,
            callAction = ToggleSpeakerphone(callDeviceState.isSpeakerphoneEnabled.not()),
            description = stringResource(R.string.stream_video_call_controls_toggle_speakerphone)
        ),
        CallControlAction(
            actionBackgroundTint = Color.White,
            icon = cameraIcon,
            iconTint = Color.DarkGray,
            callAction = ToggleCamera(callDeviceState.isCameraEnabled.not()),
            description = stringResource(R.string.stream_video_call_controls_toggle_camera)
        ),
        CallControlAction(
            actionBackgroundTint = Color.White,
            icon = microphoneIcon,
            iconTint = Color.DarkGray,
            callAction = ToggleMicrophone(callDeviceState.isMicrophoneEnabled.not()),
            description = stringResource(R.string.stream_video_call_controls_toggle_microphone)
        ),
        CallControlAction(
            actionBackgroundTint = Color.White,
            icon = painterResource(id = R.drawable.stream_video_ic_camera_flip),
            iconTint = Color.DarkGray,
            callAction = FlipCamera,
            description = stringResource(R.string.stream_video_call_controls_flip_camera)
        ),
        CallControlAction(
            actionBackgroundTint = VideoTheme.colors.errorAccent,
            icon = painterResource(id = R.drawable.stream_video_ic_call_end),
            iconTint = Color.White,
            callAction = LeaveCall,
            description = stringResource(R.string.stream_video_call_controls_leave_call)
        ),
    )
}
