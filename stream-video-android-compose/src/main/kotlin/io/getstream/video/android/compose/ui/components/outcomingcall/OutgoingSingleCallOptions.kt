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

package io.getstream.video.android.compose.ui.components.outcomingcall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.extensions.toggleAlpha

@Composable
internal fun OutgoingSingleCallOptions(
    modifier: Modifier = Modifier,
    callId: String,
    onCancelCall: (String) -> Unit = {},
    onMicToggleChanged: (Boolean) -> Unit = {},
    onVideoToggleChanged: (Boolean) -> Unit = {},
) {
    var isMicEnabled by remember { mutableStateOf(true) }
    var isVideoEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                modifier = Modifier
                    .toggleAlpha(isMicEnabled)
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
                    val micIcon =
                        if (isMicEnabled) VideoTheme.icons.micOn else VideoTheme.icons.micOff

                    Icon(
                        painter = micIcon,
                        contentDescription = "Toggle Mic",
                        tint = VideoTheme.colors.textHighEmphasis
                    )
                }
            )

            IconButton(
                modifier = Modifier
                    .toggleAlpha(isVideoEnabled)
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
                        if (isVideoEnabled) VideoTheme.icons.videoCam else VideoTheme.icons.videoCamOff

                    Icon(
                        painter = cameraIcon,
                        contentDescription = "Toggle Video",
                        tint = VideoTheme.colors.textHighEmphasis
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        IconButton(
            modifier = Modifier
                .background(
                    color = VideoTheme.colors.errorAccent,
                    shape = VideoTheme.shapes.callButton
                )
                .size(VideoTheme.dimens.largeButtonSize),
            onClick = { onCancelCall(callId) },
            content = {
                Icon(
                    painter = VideoTheme.icons.callEnd,
                    tint = Color.White,
                    contentDescription = "End call"
                )
            }
        )
    }
}

@Preview
@Composable
private fun OutgoingCallOptionsPreview() {
    VideoTheme { OutgoingSingleCallOptions(callId = "") }
}