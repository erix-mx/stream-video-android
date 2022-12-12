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

package io.getstream.video.android.app

import android.app.Application
import android.content.Context
import io.getstream.log.StreamLog
import io.getstream.log.android.AndroidStreamLogger
import io.getstream.video.android.StreamVideo
import io.getstream.video.android.StreamVideoBuilder
import io.getstream.video.android.app.ui.call.CallService
import io.getstream.video.android.app.ui.call.XmlCallActivity
import io.getstream.video.android.input.CallActivityInput
import io.getstream.video.android.input.CallServiceInput
import io.getstream.video.android.logging.LoggingLevel
import io.getstream.video.android.token.CredentialsProvider
import io.getstream.video.android.user.UserCredentialsManager
import io.getstream.video.android.user.UserPreferences

class VideoApp : Application() {

    val userPreferences: UserPreferences by lazy {
        UserCredentialsManager.getPreferences()
    }

    lateinit var credentialsProvider: CredentialsProvider
        private set

    lateinit var streamVideo: StreamVideo
        private set

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StreamLog.setValidator { _, _ -> true }
            StreamLog.install(AndroidStreamLogger())
        }
        StreamLog.i(TAG) { "[onCreate] no args" }
        UserCredentialsManager.initialize(this)
    }

    /**
     * Sets up and returns the [streamVideo] required to connect to the API.
     */
    fun initializeStreamVideo(
        credentialsProvider: CredentialsProvider,
        loggingLevel: LoggingLevel
    ): StreamVideo {
        StreamLog.d(TAG) { "[initializeStreamCalls] loggingLevel: $loggingLevel" }
        this.credentialsProvider = credentialsProvider

        return StreamVideoBuilder(
            context = this,
            credentialsProvider = credentialsProvider,
            androidInputs = setOf(
                CallServiceInput.from(CallService::class),
//                CallActivityInput.from(CallActivity::class),
                CallActivityInput.from(XmlCallActivity::class),
            ),
            loggingLevel = loggingLevel
        ).build().also {
            streamVideo = it
            StreamLog.v(TAG) { "[initializeStreamCalls] completed" }
        }
    }

    private companion object {
        private const val TAG = "Call:App"
    }
}

internal val Context.videoApp get() = applicationContext as VideoApp
