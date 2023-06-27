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

package io.getstream.video.android.compose.ui.components.video

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import io.getstream.log.StreamLog
import io.getstream.video.android.common.renderer.StreamVideoTextureViewRenderer
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.video.VideoScalingType.Companion.toCommonScalingType
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.ParticipantState
import io.getstream.video.android.core.model.MediaTrack
import io.getstream.video.android.core.model.VideoTrack
import io.getstream.video.android.mock.StreamMockUtils
import io.getstream.video.android.mock.mockCall
import io.getstream.webrtc.android.ui.VideoTextureViewRenderer

/**
 * Renders a single video track based on the call state.
 *
 * @param call The call state that contains all the tracks and participants.
 * @param video A media contains a video track or an audio track to be rendered.
 * @param modifier Modifier for styling.
 * @param videoFallbackContent Content is shown the video track is failed to load or not available.
 * @param onRendered An interface that will be invoked when the video is rendered.
 */
@Composable
public fun VideoRenderer(
    call: Call,
    video: ParticipantState.Media?,
    modifier: Modifier = Modifier,
    videoScalingType: VideoScalingType = VideoScalingType.SCALE_ASPECT_BALANCED,
    videoFallbackContent: @Composable (Call) -> Unit = {
        DefaultMediaTrackFallbackContent(
            modifier,
            call
        )
    },
    onRendered: (View) -> Unit = {},
) {
    if (LocalInspectionMode.current) {
        Image(
            modifier = modifier
                .fillMaxSize()
                .testTag("video_renderer"),
            painter = painterResource(id = io.getstream.video.android.ui.common.R.drawable.stream_video_call_sample),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        return
    }

    if (video?.enabled == true) {
        val mediaTrack = video.track
        val sessionId = video.sessionId
        val trackType = video.type

        var view: VideoTextureViewRenderer? by remember { mutableStateOf(null) }

        DisposableEffect(call, video) {
            // inform the call that we want to render this video track. (this will trigger a subscription to the track)
            call.setVisibility(sessionId, trackType, true)

            onDispose {
                cleanTrack(view, mediaTrack)
                // inform the call that we no longer want to render this video track
                call.setVisibility(sessionId, trackType, false)
            }
        }

        if (mediaTrack != null) {
            AndroidView(
                factory = { context ->
                    StreamVideoTextureViewRenderer(context).apply {
                        call.initRenderer(
                            videoRenderer = this,
                            sessionId = sessionId,
                            trackType = trackType,
                            onRendered = onRendered
                        )
                        setScalingType(scalingType = videoScalingType.toCommonScalingType())
                        setupVideo(mediaTrack, this)

                        view = this
                    }
                },
                update = { v -> setupVideo(mediaTrack, v) },
                modifier = modifier.testTag("video_renderer"),
            )
        } else {
            // fallback when the video is available but the track didn't load yet
            videoFallbackContent.invoke(call)
        }
    } else {
        // fallback when no video is available. video.enabled is false
        videoFallbackContent.invoke(call)
    }
}

private fun cleanTrack(
    view: VideoTextureViewRenderer?,
    mediaTrack: MediaTrack?,
) {
    if (view != null && mediaTrack is VideoTrack) {
        mediaTrack.video.removeSink(view)
    }
}

private fun setupVideo(
    mediaTrack: MediaTrack?,
    renderer: VideoTextureViewRenderer,
) {
    cleanTrack(renderer, mediaTrack)

    try {
        if (mediaTrack is VideoTrack) {
            mediaTrack.video.addSink(renderer)
        }
    } catch (e: Exception) {
        StreamLog.d("VideoRenderer") { e.message.toString() }
    }
}

@Composable
private fun DefaultMediaTrackFallbackContent(
    modifier: Modifier,
    call: Call
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(VideoTheme.colors.appBackground)
            .testTag("video_renderer_fallback"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = io.getstream.video.android.ui.common.R.drawable.stream_video_ic_preview_avatar),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(30.dp),
            text = stringResource(
                id = io.getstream.video.android.ui.common.R.string.stream_video_call_rendering_failed,
                call.sessionId.orEmpty()
            ),
            color = VideoTheme.colors.textHighEmphasis,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun VideoRendererPreview() {
    StreamMockUtils.initializeStreamVideo(LocalContext.current)
    VideoTheme {
        VideoRenderer(
            call = mockCall,
            video = ParticipantState.Video(
                track = VideoTrack("", org.webrtc.VideoTrack(123)),
                enabled = true,
                sessionId = "",
            )
        )
    }
}
