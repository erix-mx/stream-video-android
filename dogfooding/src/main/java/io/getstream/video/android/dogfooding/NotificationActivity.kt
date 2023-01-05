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

package io.getstream.video.android.dogfooding

import android.content.Context
import io.getstream.video.android.StreamVideo
import io.getstream.video.android.notifications.AbstractNotificationActivity

class NotificationActivity : AbstractNotificationActivity() {

    override fun getStreamVideo(context: Context): StreamVideo {
        return dogfoodingApp.streamVideo
    }

    override fun initializeVideoIfNeeded() {
        if (!dogfoodingApp.isInitialized()) {
            val hasInitialized = dogfoodingApp.initializeFromCredentials()

            if (!hasInitialized) {
                finish()
                return
            }
        }
    }
}