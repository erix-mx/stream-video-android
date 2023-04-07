/*
 * Copyright (c) 2014-2023 Stream.io Inc. All rights reserved.
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

package io.getstream.video.android.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import io.getstream.video.android.common.util.mockParticipant
import io.getstream.video.android.common.util.mockParticipantList
import io.getstream.video.android.compose.base.BasePortraitComposeTest
import io.getstream.video.android.compose.ui.components.call.incomingcall.IncomingCallContent
import io.getstream.video.android.compose.ui.components.call.incomingcall.internal.IncomingCallDetails
import io.getstream.video.android.compose.ui.components.call.incomingcall.internal.IncomingCallOptions
import io.getstream.video.android.compose.ui.components.call.outgoingcall.OutgoingCallContent
import io.getstream.video.android.compose.ui.components.call.outgoingcall.internal.OutgoingCallDetails
import io.getstream.video.android.compose.ui.components.call.outgoingcall.internal.OutgoingGroupCallOptions
import io.getstream.video.android.compose.ui.components.video.VideoRenderer
import io.getstream.video.android.core.call.state.CallMediaState
import io.getstream.video.android.core.model.CallType
import io.getstream.video.android.core.model.CallUser
import org.junit.Test
import org.webrtc.VideoTrack
import stream.video.sfu.models.TrackType

internal class CallContentTest : BasePortraitComposeTest() {

    @Test
    fun `snapshot VideoRenderer composable`() {
        snapshot {
            VideoRenderer(
                call = null,
                videoTrack = io.getstream.video.android.core.model.VideoTrack(
                    "",
                    VideoTrack(123)
                ),
                sessionId = "",
                trackType = TrackType.TRACK_TYPE_VIDEO
            )
        }
    }

    @Test
    fun `snapshot IncomingCallContentDetails composable`() {
        snapshot {
            IncomingCallDetails(
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

    @Test
    fun `snapshot IncomingCallOptions composable`() {
        snapshotWithDarkMode {
            IncomingCallOptions(
                isVideoCall = true,
                isVideoEnabled = true,
                onCallAction = { }
            )
        }
    }

    @Test
    fun `snapshot IncomingCallContent Video type with one participant composable`() {
        snapshot {
            IncomingCallContent(
                callType = CallType.VIDEO,
                participants = listOf(
                    mockParticipant.let {
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
                ),
                isVideoEnabled = false,
                modifier = Modifier.fillMaxSize(),
                onBackPressed = {},
                onCallAction = {}
            )
        }
    }

    @Test
    fun `snapshot IncomingCallContent Video type with multiple participants composable`() {
        snapshot {
            IncomingCallContent(
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
                },
                isVideoEnabled = false,
                modifier = Modifier.fillMaxSize(),
                onBackPressed = {},
                onCallAction = {}
            )
        }
    }

    @Test
    fun `snapshot OutgoingCallDetails composable`() {
        snapshot {
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

    @Test
    fun `snapshot OutgoingCallOptions composable`() {
        snapshotWithDarkMode {
            OutgoingGroupCallOptions(
                callMediaState = CallMediaState(),
                onCallAction = { }
            )
        }
    }

    @Test
    fun `snapshot OutgoingCallContent Video type with one participant composable`() {
        snapshot {
            OutgoingCallContent(
                callType = CallType.VIDEO,
                participants = listOf(
                    mockParticipant.let {
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
                ),
                callMediaState = CallMediaState(),
                modifier = Modifier.fillMaxSize(),
                onBackPressed = {},
                onCallAction = {}
            )
        }
    }

    @Test
    fun `snapshot OutgoingCallContent Video type with multiple participants composable`() {
        snapshot {
            OutgoingCallContent(
                callType = CallType.VIDEO,
                participants =
                mockParticipantList.map {
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
                },
                callMediaState = CallMediaState(),
                modifier = Modifier.fillMaxSize(),
                onBackPressed = {},
                onCallAction = {}
            )
        }
    }
}
