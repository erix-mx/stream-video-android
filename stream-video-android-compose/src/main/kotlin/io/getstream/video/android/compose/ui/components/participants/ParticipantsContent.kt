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

package io.getstream.video.android.compose.ui.components.participants

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.model.Call

@Composable
public fun ParticipantsContent(
    call: Call,
    modifier: Modifier = Modifier,
    onRender: (View) -> Unit = {}
) {
    val roomParticipants by call.callParticipants.collectAsState(emptyList())
    val participants = roomParticipants.distinctBy { it.id }

    when (participants.size) {
        0 -> {
            Box(modifier = modifier) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = VideoTheme.icons.call,
                    contentDescription = null
                )
            }
        }
        1 -> ParticipantItem(
            modifier = modifier,
            call = call,
            participant = participants.first(),
            onRender = onRender
        )
        2 -> {
            val firstParticipant = participants.first { !it.isLocal }
            val secondParticipant = participants.first { it.isLocal }

            Column(modifier) {
                ParticipantItem(
                    modifier = Modifier.weight(1f),
                    call = call,
                    participant = firstParticipant
                )

                ParticipantItem(
                    modifier = Modifier.weight(1f),
                    call = call,
                    participant = secondParticipant,
                    onRender = onRender
                )
            }
        }
        3 -> {
            val nonLocal = participants.filter { !it.isLocal }

            val firstParticipant = nonLocal[0]
            val secondParticipant = nonLocal[1]
            val thirdParticipant = participants.first { it.isLocal }

            Column(modifier) {
                ParticipantItem(
                    modifier = Modifier.weight(1f),
                    call = call,
                    participant = firstParticipant
                )

                Row(modifier = Modifier.weight(1f)) {
                    ParticipantItem(
                        modifier = Modifier.weight(1f),
                        call = call,
                        participant = secondParticipant
                    )

                    ParticipantItem(
                        modifier = Modifier.weight(1f),
                        call = call,
                        participant = thirdParticipant,
                        onRender = onRender
                    )
                }
            }
        }
        else -> {
            /**
             * More than three participants, we only show the first four.
             */
            val nonLocal = participants.filter { !it.isLocal }.take(3)

            val firstParticipant = nonLocal[0]
            val secondParticipant = nonLocal[1]
            val thirdParticipant = nonLocal[2]
            val fourthParticipant = participants.first { it.isLocal }

            Column(modifier) {
                Row(modifier = Modifier.weight(1f)) {
                    ParticipantItem(
                        modifier = Modifier.weight(1f),
                        call = call,
                        participant = firstParticipant
                    )

                    ParticipantItem(
                        modifier = Modifier.weight(1f),
                        call = call,
                        participant = secondParticipant
                    )
                }

                Row(modifier = Modifier.weight(1f)) {
                    ParticipantItem(
                        modifier = Modifier.weight(1f),
                        call = call,
                        participant = thirdParticipant
                    )

                    ParticipantItem(
                        modifier = Modifier.weight(1f),
                        call = call,
                        participant = fourthParticipant,
                        onRender = onRender
                    )
                }
            }
        }
    }
}
