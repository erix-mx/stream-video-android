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

package io.getstream.video.android.compose.ui.components.call.outgoingcall.internal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.mock.mockParticipantList
import io.getstream.video.android.compose.ui.components.participants.internal.ParticipantAvatars
import io.getstream.video.android.compose.ui.components.participants.internal.ParticipantInformation
import io.getstream.video.android.core.model.CallParticipantState
import io.getstream.video.android.core.model.CallStatus
import io.getstream.video.android.core.model.CallType
import io.getstream.video.android.core.model.CallUser

@Composable
internal fun OutgoingCallDetails(
    modifier: Modifier = Modifier,
    callType: CallType,
    participants: List<CallUser>
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (callType == CallType.AUDIO) {
            ParticipantAvatars(
                participants = participants.map {
                    CallParticipantState(
                        id = it.id,
                        role = it.role,
                        name = it.name,
                        profileImageURL = it.imageUrl,
                        idPrefix = "",
                        sessionId = ""
                    )
                }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        ParticipantInformation(
            callStatus = CallStatus.Outgoing,
            participants = participants
        )
    }
}

@Preview
@Composable
private fun OutgoingCallDetailsPreview() {
    VideoTheme {
        OutgoingCallDetails(
            callType = CallType.VIDEO,
            participants = mockParticipantList.map {
                CallUser(
                    id = it.id,
                    name = it.name,
                    role = it.role,
                    state = null,
                    imageUrl = it.profileImageURL ?: "",
                    createdAt = null,
                    updatedAt = null,
                    teams = emptyList()
                )
            }
        )
    }
}
