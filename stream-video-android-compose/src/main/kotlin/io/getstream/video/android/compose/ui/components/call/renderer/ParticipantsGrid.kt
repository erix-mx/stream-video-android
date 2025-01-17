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

package io.getstream.video.android.compose.ui.components.call.renderer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.ParticipantState
import io.getstream.video.android.mock.StreamMockUtils
import io.getstream.video.android.mock.mockCall

/**
 * Renders all the participants, based on the number of people in a call and the call state.
 * Also takes into account if there are any screen sharing sessions active and adjusts the UI
 * accordingly.
 *
 * @param call The call that contains all the participants state and tracks.
 * @param modifier Modifier for styling.
 * @param style Defined properties for styling a single video call track.
 * @param videoRenderer A single video renderer renders each individual participant.
 */
@Composable
public fun ParticipantsGrid(
    call: Call,
    modifier: Modifier = Modifier,
    style: VideoRendererStyle = RegularVideoRendererStyle(),
    videoRenderer: @Composable (
        modifier: Modifier,
        call: Call,
        participant: ParticipantState,
        style: VideoRendererStyle,
    ) -> Unit = { videoModifier, videoCall, videoParticipant, videoStyle ->
        ParticipantVideo(
            modifier = videoModifier,
            call = videoCall,
            participant = videoParticipant,
            style = videoStyle,
        )
    },
) {
    if (LocalInspectionMode.current) {
        ParticipantsRegularGrid(
            call = call,
            modifier = modifier,
        )
        return
    }

    val screenSharingSession = call.state.screenSharingSession.collectAsStateWithLifecycle()
    val screenSharing = screenSharingSession.value

    if (screenSharing == null) {
        ParticipantsRegularGrid(
            call = call,
            modifier = modifier,
            style = style,
            videoRenderer = videoRenderer,
        )
    } else {
        ParticipantsScreenSharing(
            call = call,
            modifier = modifier,
            session = screenSharing,
            style = ScreenSharingVideoRendererStyle().copy(
                isFocused = style.isFocused,
                isShowingReactions = style.isShowingReactions,
                labelPosition = style.labelPosition,
            ),
            videoRenderer = videoRenderer,
        )
    }
}

@Preview
@Composable
private fun CallVideoRendererPreview() {
    StreamMockUtils.initializeStreamVideo(LocalContext.current)
    VideoTheme {
        ParticipantsGrid(
            call = mockCall,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
