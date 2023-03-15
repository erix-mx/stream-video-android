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

package io.getstream.video.chat_with_video_starter.ui.messages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.getstream.chat.android.compose.ui.theme.ChatTheme

class MessagesActivity : ComponentActivity() {

    // TODO initialize ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // TODO set the UI of the messages screen
        }
    }

    @Composable
    private fun CallButton() {
        Icon(
            modifier = Modifier
                .padding(end = 8.dp)
                .clickable { startCall() },
            imageVector = Icons.Default.Call,
            contentDescription = null,
            tint = ChatTheme.colors.textHighEmphasis
        )
    }

    private fun startCall() {
        // TODO - start a call and send a custom attachment
    }

    companion object {
        private const val KEY_CHANNEL_ID = "channel_id"
        fun getIntent(context: Context, channelId: String): Intent {
            return Intent(context, MessagesActivity::class.java).apply {
                putExtra(KEY_CHANNEL_ID, channelId)
            }
        }
    }
}