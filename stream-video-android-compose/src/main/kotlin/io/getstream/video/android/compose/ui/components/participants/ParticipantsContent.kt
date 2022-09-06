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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.getstream.video.android.model.CallParticipant
import io.getstream.video.android.model.Room

@Composable
public fun ParticipantsContent(
    room: Room,
    participants: List<CallParticipant>,
    modifier: Modifier = Modifier,
    localParticipant: CallParticipant
) {
    val otherParticipants = participants.filter { it.id != localParticipant.id }

    LazyRow( // TODO - build a grid of first 4 participants
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(otherParticipants) { participant ->
            ParticipantItem(
                room,
                participant
            )
        }
    }
}
