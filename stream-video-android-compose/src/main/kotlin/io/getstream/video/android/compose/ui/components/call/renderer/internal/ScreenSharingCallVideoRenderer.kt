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

package io.getstream.video.android.compose.ui.components.call.renderer.internal

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.getstream.video.android.compose.ui.components.call.renderer.CallSingleVideoRenderer
import io.getstream.video.android.compose.ui.components.call.renderer.ScreenSharingVideoRendererStyle
import io.getstream.video.android.compose.ui.components.call.renderer.VideoRendererStyle
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.ParticipantState
import io.getstream.video.android.core.model.ScreenSharingSession

/**
 * Renders all the CallParticipants, based on the number of people in a call and the call state.
 * Also takes into account if there are any screen sharing sessions active and adjusts the UI
 * accordingly.
 *
 * @param call The call that contains all the participants state and tracks.
 * @param session The screen sharing session which is active.
 * @param modifier Modifier for styling.
 * @param style Represents a regular video call render styles.
 * @param videoRenderer A single video renderer renders each individual participant.
 */
@Composable
public fun ScreenSharingCallVideoRenderer(
    call: Call,
    session: ScreenSharingSession,
    modifier: Modifier = Modifier,
    isZoomable: Boolean = true,
    style: VideoRendererStyle = ScreenSharingVideoRendererStyle(),
    videoRenderer: @Composable (
        modifier: Modifier,
        call: Call,
        participant: ParticipantState,
        style: VideoRendererStyle
    ) -> Unit = { videoModifier, videoCall, videoParticipant, videoStyle ->
        CallSingleVideoRenderer(
            modifier = videoModifier,
            call = videoCall,
            participant = videoParticipant,
            style = videoStyle
        )
    },
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation
    val screenSharingSession by call.state.screenSharingSession.collectAsStateWithLifecycle()
    val participants by call.state.participants.collectAsStateWithLifecycle()

    if (orientation == ORIENTATION_PORTRAIT) {
        PortraitScreenSharingVideoRenderer(
            call = call,
            session = session,
            participants = participants,
            dominantSpeaker = screenSharingSession?.participant,
            modifier = modifier,
            isZoomable = isZoomable,
            style = style,
            videoRenderer = videoRenderer
        )
    } else {
        LandscapeScreenSharingVideoRenderer(
            call = call,
            session = session,
            participants = participants,
            dominantSpeaker = screenSharingSession?.participant,
            modifier = modifier,
            isZoomable = isZoomable,
            style = style,
            videoRenderer = videoRenderer
        )
    }
}