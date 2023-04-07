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

import io.getstream.video.android.common.model.Speaking
import io.getstream.video.android.compose.base.BasePortraitComposeTest
import io.getstream.video.android.compose.ui.components.audio.ActiveSoundLevels
import io.getstream.video.android.compose.ui.components.audio.SoundIndicator
import org.junit.Test

internal class AudioTest : BasePortraitComposeTest() {

    @Test
    fun `snapshot SoundIndicator composable`() {
        snapshotWithDarkMode {
            SoundIndicator(state = Speaking)
        }
    }

    @Test
    fun `snapshot ActiveSoundLevels composable`() {
        snapshotWithDarkMode {
            ActiveSoundLevels()
        }
    }
}
