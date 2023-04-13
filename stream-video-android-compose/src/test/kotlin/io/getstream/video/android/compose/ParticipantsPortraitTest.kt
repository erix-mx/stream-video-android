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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import io.getstream.video.android.common.util.mockParticipant
import io.getstream.video.android.common.util.mockParticipantList
import io.getstream.video.android.common.util.mockParticipants
import io.getstream.video.android.common.util.mockVideoTrackWrapper
import io.getstream.video.android.compose.base.BaseComposeTest
import io.getstream.video.android.compose.state.ui.internal.InviteUserItemState
import io.getstream.video.android.compose.state.ui.internal.ParticipantList
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.participants.CallParticipant
import io.getstream.video.android.compose.ui.components.participants.LocalVideoContent
import io.getstream.video.android.compose.ui.components.participants.ParticipantVideo
import io.getstream.video.android.compose.ui.components.participants.internal.CallParticipantsInfoAppBar
import io.getstream.video.android.compose.ui.components.participants.internal.CallParticipantsInfoOptions
import io.getstream.video.android.compose.ui.components.participants.internal.CallParticipantsList
import io.getstream.video.android.compose.ui.components.participants.internal.InviteUserList
import io.getstream.video.android.compose.ui.components.participants.internal.ParticipantAvatars
import io.getstream.video.android.compose.ui.components.participants.internal.ParticipantInformation
import io.getstream.video.android.compose.ui.components.participants.internal.ParticipantsColumn
import io.getstream.video.android.compose.ui.components.participants.internal.PortraitParticipants
import io.getstream.video.android.compose.ui.components.participants.internal.PortraitScreenSharingContent
import io.getstream.video.android.core.model.CallStatus
import io.getstream.video.android.core.model.CallType
import io.getstream.video.android.core.model.ScreenSharingSession
import org.junit.Rule
import org.junit.Test

internal class ParticipantsPortraitTest : BaseComposeTest() {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_4A)

    override fun basePaparazzi(): Paparazzi = paparazzi

    @Test
    fun `snapshot ParticipantAvatars composable`() {
        snapshotWithDarkMode {
            ParticipantAvatars(participants = mockParticipantList)
        }
    }

    @Test
    fun `snapshot ParticipantInformation composable`() {
        snapshotWithDarkMode {
            ParticipantInformation(
                callType = CallType.VIDEO,
                callStatus = CallStatus.Incoming,
                participants = mockParticipants,
            )
        }
    }

    @Test
    fun `snapshot InviteUserList composable`() {
        snapshotWithDarkMode {
            InviteUserList(
                mockParticipantList.map { InviteUserItemState(it.user.value) },
                onUserSelected = {}
            )
        }
    }

    @Test
    fun `snapshot CallParticipantsInfoOptions composable`() {
        snapshotWithDarkMode {
            CallParticipantsInfoOptions(
                isCurrentUserMuted = false,
                onOptionSelected = { }
            )
        }
    }

    @Test
    fun `snapshot CallParticipantsInfoAppBar composable`() {
        snapshotWithDarkMode {
            CallParticipantsInfoAppBar(
                numberOfParticipants = 10,
                infoStateMode = ParticipantList,
                onBackPressed = {}
            ) {}
        }
    }

    @Test
    fun `snapshot CallParticipant a local call composable`() {
        snapshot {
            CallParticipant(
                call = null,
                participant = mockParticipantList[0],
                isFocused = true
            )
        }
    }

    @Test
    fun `snapshot CallParticipant a remote call composable`() {
        snapshot {
            CallParticipant(
                call = null,
                participant = mockParticipantList[1],
                isFocused = true
            )
        }
    }

    @Test
    fun `snapshot ParticipantVideo composable`() {
        snapshot {
            ParticipantVideo(
                call = null,
                participant = mockParticipant
            ) {}
        }
    }

    @Test
    fun `snapshot LocalVideoContent composable`() {
        snapshot {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val screenHeight = configuration.screenHeightDp
            LocalVideoContent(
                call = null,
                modifier = Modifier.fillMaxSize(),
                localParticipant = mockParticipant,
                parentBounds = IntSize(screenWidth, screenHeight),
                paddingValues = PaddingValues(0.dp)
            )
        }
    }

    @Test
    fun `snapshot CallParticipantsList composable`() {
        snapshotWithDarkMode {
            CallParticipantsList(
                participantsState = mockParticipantList,
                onUserOptionsSelected = {}
            )
        }
    }

    @Test
    fun `snapshot PortraitParticipants1 composable`() {
        snapshot {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val screenHeight = configuration.screenHeightDp
            val participants = mockParticipants

            Box(
                modifier = Modifier.background(color = VideoTheme.colors.appBackground)
            ) {
                PortraitParticipants(
                    call = null,
                    primarySpeaker = participants[0],
                    callParticipants = participants.take(1),
                    modifier = Modifier.fillMaxSize(),
                    paddingValues = PaddingValues(0.dp),
                    parentSize = IntSize(screenWidth, screenHeight)
                ) {}
            }
        }
    }

    @Test
    fun `snapshot PortraitParticipants2 composable`() {
        snapshot {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val screenHeight = configuration.screenHeightDp
            val participants = mockParticipants

            Box(
                modifier = Modifier.background(color = VideoTheme.colors.appBackground)
            ) {
                PortraitParticipants(
                    call = null,
                    primarySpeaker = participants[0],
                    callParticipants = participants.take(2),
                    modifier = Modifier.fillMaxSize(),
                    paddingValues = PaddingValues(0.dp),
                    parentSize = IntSize(screenWidth, screenHeight)
                ) {}
            }
        }
    }

    @Test
    fun `snapshot PortraitParticipants3 composable`() {
        snapshot {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val screenHeight = configuration.screenHeightDp
            val participants = mockParticipants

            Box(
                modifier = Modifier.background(color = VideoTheme.colors.appBackground)
            ) {
                PortraitParticipants(
                    call = null,
                    primarySpeaker = participants[0],
                    callParticipants = participants.take(3),
                    modifier = Modifier.fillMaxSize(),
                    paddingValues = PaddingValues(0.dp),
                    parentSize = IntSize(screenWidth, screenHeight)
                ) {}
            }
        }
    }

    @Test
    fun `snapshot PortraitParticipants4 composable`() {
        snapshot {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val screenHeight = configuration.screenHeightDp
            val participants = mockParticipants

            Box(
                modifier = Modifier.background(color = VideoTheme.colors.appBackground)
            ) {
                PortraitParticipants(
                    call = null,
                    primarySpeaker = participants[0],
                    callParticipants = participants.take(4),
                    modifier = Modifier.fillMaxSize(),
                    paddingValues = PaddingValues(0.dp),
                    parentSize = IntSize(screenWidth, screenHeight)
                ) {}
            }
        }
    }

    @Test
    fun `snapshot PortraitParticipants5 composable`() {
        snapshot {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val screenHeight = configuration.screenHeightDp
            val participants = mockParticipants

            Box(
                modifier = Modifier.background(color = VideoTheme.colors.appBackground)
            ) {
                PortraitParticipants(
                    call = null,
                    primarySpeaker = participants[0],
                    callParticipants = participants.take(5),
                    modifier = Modifier.fillMaxSize(),
                    paddingValues = PaddingValues(0.dp),
                    parentSize = IntSize(screenWidth, screenHeight)
                ) {}
            }
        }
    }

    @Test
    fun `snapshot PortraitParticipants6 composable`() {
        snapshot {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val screenHeight = configuration.screenHeightDp
            val participants = mockParticipants

            Box(
                modifier = Modifier.background(color = VideoTheme.colors.appBackground)
            ) {
                PortraitParticipants(
                    call = null,
                    primarySpeaker = participants[0],
                    callParticipants = participants.take(6),
                    modifier = Modifier.fillMaxSize(),
                    paddingValues = PaddingValues(0.dp),
                    parentSize = IntSize(screenWidth, screenHeight)
                ) {}
            }
        }
    }

    @Test
    fun `snapshot PortraitScreenSharingContent for other participant composable`() {
        snapshot(isInDarkMode = true) {
            PortraitScreenSharingContent(
                call = null,
                session = ScreenSharingSession(
                    track = mockParticipantList[1].videoTrackWrapped ?: mockVideoTrackWrapper,
                    participant = mockParticipantList[1]
                ),
                participants = mockParticipantList,
                primarySpeaker = mockParticipantList[1],
                paddingValues = PaddingValues(0.dp),
                modifier = Modifier.fillMaxSize(),
                onBackPressed = {},
                onCallAction = {},
                onRender = {}
            )
        }
    }

    @Test
    fun `snapshot PortraitScreenSharingContent for myself composable`() {
        snapshot(isInDarkMode = true) {
            PortraitScreenSharingContent(
                call = null,
                session = ScreenSharingSession(
                    track = mockParticipantList[0].videoTrackWrapped ?: mockVideoTrackWrapper,
                    participant = mockParticipantList[0]
                ),
                participants = mockParticipantList,
                primarySpeaker = mockParticipantList[0],
                paddingValues = PaddingValues(0.dp),
                modifier = Modifier.fillMaxSize(),
                onRender = {},
                onBackPressed = {},
                onCallAction = {}
            )
        }
    }

    @Test
    fun `snapshot ParticipantsColumn composable`() {
        snapshotWithDarkModeRow {
            ParticipantsColumn(
                call = null,
                participants = mockParticipantList,
                primarySpeaker = mockParticipant,
            )
        }
    }
}
