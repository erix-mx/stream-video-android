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

package io.getstream.video.android.compose.ui.components.calling.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.getstream.video.android.compose.R
import io.getstream.video.android.compose.theme.VideoTheme

@Composable
internal fun VideoCallingOptions(
    modifier: Modifier = Modifier,
    callId: String,
    onEndCall: (String) -> Unit,
    onChatMessagesExpand: () -> Unit = {},
    onMicToggleChanged: (Boolean) -> Unit,
    onVideoToggleChanged: (Boolean) -> Unit,
    onCameraOrientationChanged: (Boolean) -> Unit = {},
) {
    var isMicEnabled by remember { mutableStateOf(true) }
    var isVideoEnabled by remember { mutableStateOf(true) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            modifier = Modifier
                .background(
                    color = VideoTheme.colors.appBackground,
                    shape = VideoTheme.shapes.callButton
                )
                .size(VideoTheme.dimens.mediumButtonSize),
            onClick = onChatMessagesExpand,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_message),
                    contentDescription = "Expand Chats",
                    tint = VideoTheme.colors.textHighEmphasis
                )
            }
        )

        IconButton(
            modifier = Modifier
                .alpha(if (isVideoEnabled) 1.0f else 0.4f)
                .background(
                    color = VideoTheme.colors.appBackground,
                    shape = VideoTheme.shapes.callButton
                )
                .size(VideoTheme.dimens.mediumButtonSize),
            onClick = {
                isVideoEnabled = !isVideoEnabled
                onVideoToggleChanged(isVideoEnabled)
            },
            content = {
                val cameraIcon =
                    if (isVideoEnabled) R.drawable.ic_videocam else R.drawable.ic_videocam_off

                Icon(
                    painter = painterResource(id = cameraIcon),
                    contentDescription = "Toggle Video",
                    tint = VideoTheme.colors.textHighEmphasis
                )
            }
        )

        IconButton(
            modifier = Modifier
                .alpha(if (isMicEnabled) 1.0f else 0.4f)
                .background(
                    color = VideoTheme.colors.appBackground,
                    shape = VideoTheme.shapes.callButton
                )
                .size(VideoTheme.dimens.mediumButtonSize),
            onClick = {
                isMicEnabled = !isMicEnabled
                onMicToggleChanged(isMicEnabled)
            },
            content = {
                val cameraIcon =
                    if (isMicEnabled) R.drawable.ic_mic_on else R.drawable.ic_mic_off

                Icon(
                    painter = painterResource(id = cameraIcon),
                    contentDescription = "Toggle Mic",
                    tint = VideoTheme.colors.textHighEmphasis
                )
            }
        )

        IconButton(
            modifier = Modifier
                .alpha(0.15f)
                .background(
                    color = VideoTheme.colors.appBackground,
                    shape = VideoTheme.shapes.callButton
                )
                .size(VideoTheme.dimens.mediumButtonSize),
            onClick = { onCameraOrientationChanged(false) },
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_rotate),
                    contentDescription = "Rotate Camera",
                    tint = VideoTheme.colors.textHighEmphasis
                )
            }
        )

        IconButton(
            modifier = Modifier
                .background(
                    color = VideoTheme.colors.errorAccent,
                    shape = VideoTheme.shapes.callButton
                )
                .size(VideoTheme.dimens.mediumButtonSize),
            onClick = { onEndCall(callId) },
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_call_end),
                    tint = Color.White,
                    contentDescription = "End call"
                )
            }
        )
    }
}

@Preview
@Composable
private fun VideoCallingOptionsPreview() {
    VideoTheme {
        VideoCallingOptions(
            callId = "",
            onEndCall = {},
            onMicToggleChanged = {},
            onVideoToggleChanged = {}
        )
    }
}