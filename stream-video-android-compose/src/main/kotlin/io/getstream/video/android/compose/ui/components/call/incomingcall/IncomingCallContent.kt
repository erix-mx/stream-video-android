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

package io.getstream.video.android.compose.ui.components.call.incomingcall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.getstream.video.android.call.state.CallAction
import io.getstream.video.android.call.state.CallMediaState
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.background.CallBackground
import io.getstream.video.android.compose.ui.components.call.CallAppBar
import io.getstream.video.android.compose.ui.components.call.incomingcall.internal.IncomingCallDetails
import io.getstream.video.android.compose.ui.components.call.incomingcall.internal.IncomingCallOptions
import io.getstream.video.android.compose.ui.components.mock.mockParticipantList
import io.getstream.video.android.model.CallType
import io.getstream.video.android.model.CallUser
import io.getstream.video.android.viewmodel.CallViewModel

/**
 * Represents the Incoming Call state and UI, when the user receives a call from other people.
 *
 * @param viewModel The [CallViewModel] used to provide state and various handlers in the call.
 * @param modifier Modifier for styling.
 * @param onBackPressed Handler when the user taps on the back button.
 * @param onCallInfoSelected Handler when the call participants info is selected.
 * @param onCallAction Handler used when the user interacts with Call UI.
 */
@Composable
public fun IncomingCallContent(
    viewModel: CallViewModel,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onCallInfoSelected: () -> Unit = viewModel::showCallInfo,
    onCallAction: (CallAction) -> Unit = viewModel::onCallAction,
) {
    val callType: CallType by viewModel.callType.collectAsState()
    val participants: List<CallUser> by viewModel.participants.collectAsState()
    val callMediaState: CallMediaState by viewModel.callMediaState.collectAsState()

    IncomingCall(
        modifier = modifier,
        participants = participants,
        isVideoEnabled = callMediaState.isCameraEnabled,
        callType = callType,
        onBackPressed = onBackPressed,
        onCallInfoSelected = onCallInfoSelected,
        onCallAction = onCallAction
    )
}

/**
 * Stateless variant of the Incoming call UI, which you can use to build your own custom logic that
 * powers the state and handlers.
 *
 * @param participants People participating in the call.
 * @param callType The type of call, Audio or Video.
 * @param isVideoEnabled Whether the video should be enabled when entering the call or not.
 * @param modifier Modifier for styling.
 * @param onBackPressed Handler when the user taps on the back button.
 * @param onCallInfoSelected Handler when the call participants info is selected.
 * @param onCallAction Handler used when the user interacts with Call UI.
 */
@Composable
public fun IncomingCall(
    participants: List<CallUser>,
    callType: CallType,
    isVideoEnabled: Boolean,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onCallInfoSelected: () -> Unit,
    onCallAction: (CallAction) -> Unit,
) {
    CallBackground(
        modifier = modifier,
        participants = participants,
        callType = callType,
        isIncoming = true
    ) {
        Column {

            CallAppBar(onBackPressed = onBackPressed, onCallInfoSelected = onCallInfoSelected)

            val topPadding = if (participants.size == 1) {
                VideoTheme.dimens.singleAvatarAppbarPadding
            } else {
                VideoTheme.dimens.avatarAppbarPadding
            }

            IncomingCallDetails(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = topPadding),
                participants = participants
            )
        }

        IncomingCallOptions(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = VideoTheme.dimens.incomingCallOptionsBottomPadding),
            isVideoCall = callType == CallType.VIDEO,
            isVideoEnabled = isVideoEnabled,
            onCallAction = onCallAction
        )
    }
}

@Preview
@Composable
private fun IncomingCallPreview() {
    VideoTheme {
        IncomingCall(
            participants = mockParticipantList.map {
                CallUser(
                    id = it.id,
                    name = it.name,
                    role = it.role,
                    state = null,
                    createdAt = null,
                    updatedAt = null,
                    imageUrl = it.profileImageURL ?: "",
                    teams = emptyList()
                )
            },
            isVideoEnabled = false,
            callType = CallType.VIDEO,
            onCallAction = {},
            onBackPressed = {},
            onCallInfoSelected = {}
        )
    }
}
