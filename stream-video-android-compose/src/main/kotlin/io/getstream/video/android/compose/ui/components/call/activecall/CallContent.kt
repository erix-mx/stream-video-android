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

@file:OptIn(ExperimentalPermissionsApi::class)

package io.getstream.video.android.compose.ui.components.call.activecall

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import io.getstream.video.android.common.viewmodel.CallViewModel
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.CallAppBar
import io.getstream.video.android.compose.ui.components.call.controls.ControlActions
import io.getstream.video.android.compose.ui.components.call.controls.actions.DefaultOnCallActionHandler
import io.getstream.video.android.compose.ui.components.call.renderer.ParticipantVideo
import io.getstream.video.android.compose.ui.components.call.renderer.ParticipantsGrid
import io.getstream.video.android.compose.ui.components.call.renderer.RegularVideoRendererStyle
import io.getstream.video.android.compose.ui.components.call.renderer.VideoRendererStyle
import io.getstream.video.android.compose.ui.components.video.VideoRenderer
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.ParticipantState
import io.getstream.video.android.core.call.state.CallAction
import io.getstream.video.android.mock.StreamMockUtils
import io.getstream.video.android.mock.mockCall

/**
 * Represents the UI in an Active call that shows participants and their video, as well as some
 * extra UI features to control the call settings, browse participants and more.
 *
 * @param callViewModel The ViewModel required to fetch the call states and render the UI.
 * @param modifier Modifier for styling.
 * @param onBackPressed Handler when the user taps on the back button.
 * @param onCallAction Handler when the user triggers a Call Control Action.
 * @param permissions Android permissions that should be required to render a video call properly.
 * @param callAppBarContent Content is shown that calls information or additional actions.
 * @param style Represents a regular video call render styles.
 * @param videoRenderer A single video renderer renders each individual participant.
 * @param callVideoContent Content is shown that renders all participants' videos.
 * @param callControlsContent Content is shown that allows users to trigger different actions to control a joined call.
 * @param pictureInPictureContent Content shown when the user enters Picture in Picture mode, if
 * it's been enabled in the app.
 */
@Composable
public fun CallContent(
    call: Call,
    callViewModel: CallViewModel,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onCallAction: (CallAction) -> Unit = { DefaultOnCallActionHandler.onCallAction(call, it) },
    permissions: List<String> = listOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.RECORD_AUDIO
    ),
    callAppBarContent: @Composable (call: Call) -> Unit = {
        CallAppBar(
            call = call,
            leadingContent = null,
            onCallAction = onCallAction
        )
    },
    style: VideoRendererStyle = RegularVideoRendererStyle(),
    videoRenderer: @Composable (
        modifier: Modifier,
        call: Call,
        participant: ParticipantState,
        style: VideoRendererStyle
    ) -> Unit = { videoModifier, videoCall, videoParticipant, videoStyle ->
        ParticipantVideo(
            modifier = videoModifier,
            call = videoCall,
            participant = videoParticipant,
            style = videoStyle
        )
    },
    callVideoContent: @Composable RowScope.(call: Call) -> Unit = {
        ParticipantsGrid(
            call = call,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            style = style,
            videoRenderer = videoRenderer
        )
    },
    callControlsContent: @Composable (call: Call) -> Unit = {
        ControlActions(
            call = call,
            onCallAction = onCallAction
        )
    },
    pictureInPictureContent: @Composable (Call) -> Unit = { DefaultPictureInPictureContent(it) }
) {
    val isInPiPMode by callViewModel.isInPictureInPicture.collectAsStateWithLifecycle()
    val isShowingCallInfo by callViewModel.isShowingCallInfoMenu.collectAsStateWithLifecycle()

    val backAction = {
        if (isShowingCallInfo) {
            callViewModel.dismissCallInfoMenu()
        } else {
            onBackPressed()
        }
    }

    BackHandler { backAction() }

    CallContent(
        modifier = modifier,
        call = call,
        isInPictureInPicture = isInPiPMode,
        permissions = permissions,
        onCallAction = onCallAction,
        callAppBarContent = callAppBarContent,
        callVideoContent = callVideoContent,
        callControlsContent = callControlsContent,
        pictureInPictureContent = pictureInPictureContent
    )
}

/**
 * Represents the UI in an Active call that shows participants and their video, as well as some
 * extra UI features to control the call settings, browse participants and more.
 *
 * @param call The call includes states and will be rendered with participants.
 * @param modifier Modifier for styling.
 * @param isInPictureInPicture If the user has engaged in Picture-In-Picture mode.
 * @param permissions Android permissions that should be required to render a video call properly.*
 * @param onCallAction Handler when the user triggers a Call Control Action.
 * @param callAppBarContent Content is shown that calls information or additional actions.
 * @param style Represents a regular video call render styles.
 * @param videoRenderer A single video renderer renders each individual participant.
 * @param callVideoContent Content is shown that renders all participants' videos.
 * @param callControlsContent Content is shown that allows users to trigger different actions to control a joined call.
 * @param pictureInPictureContent Content shown when the user enters Picture in Picture mode, if
 * it's been enabled in the app.
 */
@Composable
public fun CallContent(
    call: Call,
    modifier: Modifier = Modifier,
    isShowingOverlayCallAppBar: Boolean = true,
    isInPictureInPicture: Boolean = false,
    permissions: List<String> = listOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.RECORD_AUDIO
    ),
    onCallAction: (CallAction) -> Unit = { DefaultOnCallActionHandler.onCallAction(call, it) },
    callAppBarContent: @Composable (call: Call) -> Unit = {
        CallAppBar(
            call = call,
            leadingContent = null,
            onCallAction = onCallAction
        )
    },
    style: VideoRendererStyle = RegularVideoRendererStyle(),
    videoRenderer: @Composable (
        modifier: Modifier,
        call: Call,
        participant: ParticipantState,
        style: VideoRendererStyle
    ) -> Unit = { videoModifier, videoCall, videoParticipant, videoStyle ->
        ParticipantVideo(
            modifier = videoModifier,
            call = videoCall,
            participant = videoParticipant,
            style = videoStyle
        )
    },
    callVideoContent: @Composable RowScope.(call: Call) -> Unit = {
        ParticipantsGrid(
            call = call,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            style = style,
            videoRenderer = videoRenderer
        )
    },
    callControlsContent: @Composable (call: Call) -> Unit = {
        ControlActions(
            call = call,
            onCallAction = onCallAction
        )
    },
    pictureInPictureContent: @Composable (Call) -> Unit = { DefaultPictureInPictureContent(it) }
) {
    val orientation = LocalConfiguration.current.orientation

    DefaultPermissionHandler(permissions = permissions)

    if (!isInPictureInPicture) {
        Scaffold(
            modifier = modifier,
            contentColor = VideoTheme.colors.appBackground,
            topBar = { },
            bottomBar = {
                if (orientation != ORIENTATION_LANDSCAPE) {
                    callControlsContent.invoke(call)
                }
            },
            content = {
                val paddings = PaddingValues(
                    top = it.calculateTopPadding(),
                    start = it.calculateStartPadding(layoutDirection = LocalLayoutDirection.current),
                    end = it.calculateEndPadding(layoutDirection = LocalLayoutDirection.current),
                    bottom = (it.calculateBottomPadding() - VideoTheme.dimens.callControllerBottomPadding)
                        .coerceAtLeast(0.dp)
                )

                Row(
                    modifier = modifier
                        .background(color = VideoTheme.colors.appBackground)
                        .padding(paddings)
                ) {
                    callVideoContent.invoke(this, call)

                    if (orientation == ORIENTATION_LANDSCAPE) {
                        callControlsContent.invoke(call)
                    }
                }

                if (isShowingOverlayCallAppBar) {
                    callAppBarContent.invoke(call)
                }
            }
        )
    } else {
        pictureInPictureContent(call)
    }
}

/**
 * Renders the default PiP content, using the call state that's provided.
 *
 * @param call The state of the call, with its participants.
 */
@Composable
internal fun DefaultPictureInPictureContent(call: Call) {
    val screenSharingSession by call.state.screenSharingSession.collectAsStateWithLifecycle()
    val session = screenSharingSession
    val video = session?.participant?.video?.collectAsStateWithLifecycle()
    val pictureInPictureAspectRatio: Float = 16f / 9f

    if (session != null) {
        VideoRenderer(
            modifier = Modifier.aspectRatio(pictureInPictureAspectRatio, false),
            call = call,
            video = video?.value
        )
    } else {
        val activeSpeakers by call.state.activeSpeakers.collectAsStateWithLifecycle()
        val me by call.state.me.collectAsStateWithLifecycle()

        if (activeSpeakers.isNotEmpty()) {
            ParticipantVideo(
                call = call,
                participant = activeSpeakers.first(),
                style = RegularVideoRendererStyle(labelPosition = Alignment.BottomStart)
            )
        } else if (me != null) {
            ParticipantVideo(
                call = call,
                participant = me!!,
                style = RegularVideoRendererStyle(labelPosition = Alignment.BottomStart)
            )
        }
    }
}

@Composable
private fun DefaultPermissionHandler(
    permissions: List<String> = listOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.RECORD_AUDIO
    ),
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions = permissions)

    LaunchedEffect(key1 = multiplePermissionsState) {
        multiplePermissionsState.launchMultiplePermissionRequest()
    }
}

@Preview
@Composable
private fun CallContentPreview() {
    StreamMockUtils.initializeStreamVideo(LocalContext.current)
    VideoTheme {
        CallContent(call = mockCall)
    }
}
