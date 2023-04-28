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

package io.getstream.video.android.compose.ui.components.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import io.getstream.video.android.compose.state.ui.participants.ChangeMuteState
import io.getstream.video.android.compose.state.ui.participants.InviteUsers
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.compose.ui.components.call.activecall.DefaultPictureInPictureContent
import io.getstream.video.android.compose.ui.components.call.activecall.internal.InviteUsersDialog
import io.getstream.video.android.compose.ui.components.call.controls.CallControls
import io.getstream.video.android.compose.ui.components.call.incomingcall.IncomingCallContent
import io.getstream.video.android.compose.ui.components.call.outgoingcall.OutgoingCallContent
import io.getstream.video.android.compose.ui.components.participants.CallParticipantsInfoMenu
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.RingingState
import io.getstream.video.android.core.call.state.CallAction
import io.getstream.video.android.core.call.state.CallDeviceState
import io.getstream.video.android.core.call.state.InviteUsersToCall
import io.getstream.video.android.core.call.state.ToggleMicrophone
import io.getstream.video.android.core.model.User
import io.getstream.video.android.core.viewmodel.CallViewModel

/**
 * Represents different call content based on the call state provided from the [callViewModel].
 *
 * The user can be in an Active Call state, if they've full joined the call, an Incoming Call state,
 * if they're being invited to join a call, or Outgoing Call state, if they're inviting other people
 * to join. Based on that, we show [CallContent], [IncomingCallContent] or [OutgoingCallContent],
 * respectively.
 *
 * @param callViewModel The [CallViewModel] used to provide state and various handlers in the call.
 * @param modifier Modifier for styling.
 * @param onBackPressed Handler when the user taps on the back button.
 * @param onCallAction Handler when the user clicks on some of the call controls.
 * @param callControlsContent Content shown for the
 * [CallControls] part of the UI.
 * @param pictureInPictureContent Content shown when the user enters Picture in Picture mode, if
 * it's been enabled in the app.
 * @param incomingCallContent Content shown when we're receiving a [Call].
 * @param outgoingCallContent Content shown when we're ringing other people.
 * @param callContent Content shown when we're connected to a [Call] successfully.
 */
@Composable
public fun CallContainer(
    callViewModel: CallViewModel,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onCallAction: (CallAction) -> Unit = callViewModel::onCallAction,
    callControlsContent: @Composable (call: Call) -> Unit = {
        CallControls(
            callViewModel = callViewModel,
            onCallAction = onCallAction,
        )
    },
    pictureInPictureContent: @Composable (call: Call) -> Unit = { DefaultPictureInPictureContent(it) },
    incomingCallContent: @Composable (call: Call) -> Unit = {
        IncomingCallContent(
            modifier = modifier.testTag("incoming_call_content"),
            callViewModel = callViewModel,
            onBackPressed = onBackPressed,
            onCallAction = onCallAction
        )
    },
    outgoingCallContent: @Composable (call: Call) -> Unit = {
        OutgoingCallContent(
            modifier = modifier.testTag("outgoing_call_content"),
            callViewModel = callViewModel,
            onBackPressed = onBackPressed,
            onCallAction = onCallAction
        )
    },
    callContent: @Composable (call: Call) -> Unit = {
        DefaultCallContent(
            modifier = modifier.testTag("call_content"),
            callViewModel = callViewModel,
            onBackPressed = onBackPressed,
            onCallAction = onCallAction,
            callControlsContent = callControlsContent,
            pictureInPictureContent = pictureInPictureContent
        )
    }
) {
    val callDeviceState by callViewModel.callDeviceState.collectAsState()

    CallContainer(
        call = callViewModel.call,
        callDeviceState = callDeviceState,
        modifier = modifier,
        onBackPressed = onBackPressed,
        onCallAction = onCallAction,
        callControlsContent = callControlsContent,
        pictureInPictureContent = pictureInPictureContent,
        incomingCallContent = incomingCallContent,
        outgoingCallContent = outgoingCallContent,
        callContent = callContent,
    )
}

@Composable
public fun CallContainer(
    call: Call,
    callDeviceState: CallDeviceState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onCallAction: (CallAction) -> Unit = {},
    callControlsContent: @Composable (call: Call) -> Unit = {
        CallControls(
            callDeviceState = callDeviceState,
            onCallAction = onCallAction
        )
    },
    pictureInPictureContent: @Composable (call: Call) -> Unit = { DefaultPictureInPictureContent(it) },
    incomingCallContent: @Composable (call: Call) -> Unit = {
        IncomingCallContent(
            call = call,
            callDeviceState = callDeviceState,
            modifier = modifier.testTag("incoming_call_content"),
            onBackPressed = onBackPressed,
            onCallAction = onCallAction
        )
    },
    outgoingCallContent: @Composable (call: Call) -> Unit = {
        OutgoingCallContent(
            call = call,
            callDeviceState = callDeviceState,
            modifier = modifier.testTag("outgoing_call_content"),
            onBackPressed = onBackPressed,
            onCallAction = onCallAction
        )
    },
    callContent: @Composable (call: Call) -> Unit = {
        CallContent(
            call = call,
            modifier = modifier.testTag("call_content"),
            callDeviceState = callDeviceState,
            onBackPressed = onBackPressed,
            onCallAction = onCallAction,
            callControlsContent = callControlsContent,
            pictureInPictureContent = pictureInPictureContent
        )
    }
) {
    val ringingStateHolder = call.state.ringingState.collectAsState(initial = RingingState.Idle)
    val ringingState = ringingStateHolder.value

    if (ringingState is RingingState.Incoming && !ringingState.acceptedByMe) {
        incomingCallContent.invoke(call)
    } else if (ringingState is RingingState.Outgoing && !ringingState.acceptedByCallee) {
        outgoingCallContent.invoke(call)
    } else {
        callContent.invoke(call)
    }
}

@Composable
internal fun DefaultCallContent(
    callViewModel: CallViewModel,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onCallAction: (CallAction) -> Unit = callViewModel::onCallAction,
    callControlsContent: @Composable (call: Call) -> Unit,
    pictureInPictureContent: @Composable (call: Call) -> Unit = { DefaultPictureInPictureContent(it) }
) {
    CallContent(
        modifier = modifier,
        callViewModel = callViewModel,
        onBackPressed = onBackPressed,
        onCallAction = onCallAction,
        callControlsContent = callControlsContent,
        pictureInPictureContent = pictureInPictureContent
    )

    val isShowingParticipantsInfo by callViewModel.isShowingCallInfoMenu.collectAsState()
    val participantsState by callViewModel.call.state.participants.collectAsState(initial = emptyList())
    var usersToInvite by remember { mutableStateOf(emptyList<User>()) }

    if (isShowingParticipantsInfo && participantsState.isNotEmpty()) {
        CallParticipantsInfoMenu(
            modifier = Modifier
                .fillMaxSize()
                .background(VideoTheme.colors.appBackground),
            participantsState = participantsState,
            onDismiss = { callViewModel.dismissCallInfoMenu() },
        ) { action ->
            when (action) {
                is InviteUsers -> {
                    usersToInvite = action.users
                    callViewModel.dismissCallInfoMenu()
                }

                is ChangeMuteState -> onCallAction(ToggleMicrophone(action.isEnabled))
            }
        }
    }

    if (usersToInvite.isNotEmpty()) {
        InviteUsersDialog(
            users = usersToInvite,
            onDismiss = { usersToInvite = emptyList() },
            onInviteUsers = {
                usersToInvite = emptyList()
                callViewModel.onCallAction(InviteUsersToCall(it))
            }
        )
    }
}
