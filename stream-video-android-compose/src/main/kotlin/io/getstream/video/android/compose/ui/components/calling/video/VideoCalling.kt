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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.getstream.video.android.compose.ui.components.CallTopAppbar
import io.getstream.video.android.model.VideoParticipant

@Composable
public fun VideoCalling(
    callId: String,
    participants: List<VideoParticipant>,
    onEndCall: (String) -> Unit,
    onChatMessagesExpand: () -> Unit = {},
    onMicToggleChanged: (Boolean) -> Unit,
    onVideoToggleChanged: (Boolean) -> Unit,
    onCameraOrientationChanged: (Boolean) -> Unit = {},
) {

    Box {

        CallTopAppbar()

        VideoCallingDetails(
            participants = participants
        )

        VideoCallingOptions(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 44.dp),
            callId = callId,
            onEndCall = onEndCall,
            onChatMessagesExpand = onChatMessagesExpand,
            onMicToggleChanged = onMicToggleChanged,
            onVideoToggleChanged = onVideoToggleChanged,
            onCameraOrientationChanged = onCameraOrientationChanged
        )
    }
}