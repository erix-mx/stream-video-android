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

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import io.getstream.android.push.firebase.FirebasePushDeviceGenerator
import io.getstream.log.StreamLog
import io.getstream.log.android.AndroidStreamLogger
import io.getstream.video.android.BuildConfig
import io.getstream.video.android.StreamVideo
import io.getstream.video.android.StreamVideoBuilder
import io.getstream.video.android.input.CallActivityInput
import io.getstream.video.android.input.CallServiceInput
import io.getstream.video.android.logging.LoggingLevel
import io.getstream.video.android.token.AuthCredentialsProvider
import io.getstream.video.android.token.CredentialsProvider
import io.getstream.video.android.user.UserCredentialsManager
import io.getstream.video.android.user.UserPreferences

class DogfoodingApp : Application() {

    private var credentials: CredentialsProvider? = null
    private var video: StreamVideo? = null

    val credentialsProvider: CredentialsProvider
        get() = requireNotNull(credentials)

    val streamVideo: StreamVideo
        get() = requireNotNull(video)

    val userPreferences: UserPreferences by lazy {
        UserCredentialsManager.getPreferences()
    }

    fun isInitialized(): Boolean {
        return video != null
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StreamLog.setValidator { _, _ -> true }
            StreamLog.install(AndroidStreamLogger())
        }
        UserCredentialsManager.initialize(this)
    }

    /**
     * Sets up and returns the [streamVideo] required to connect to the API.
     */
    fun initializeStreamVideo(
        credentialsProvider: CredentialsProvider,
        loggingLevel: LoggingLevel
    ): StreamVideo {
        this.credentials = credentialsProvider

        return StreamVideoBuilder(
            context = this,
            credentialsProvider = credentialsProvider,
            loggingLevel = loggingLevel,
            pushDeviceGenerators = listOf(FirebasePushDeviceGenerator()),
            androidInputs = setOf(
                CallServiceInput.from(CallService::class),
                CallActivityInput.from(CallActivity::class),
            )
        ).build().also {
            video = it
        }
    }

    fun logOut() {
        FirebaseAuth.getInstance().signOut()
        streamVideo.clearCallState()
        streamVideo.removeDevices(userPreferences.getDevices())
        userPreferences.clear()
        video = null
    }

    fun initializeFromCredentials(): Boolean {
        val credentials = UserCredentialsManager.initialize(this)
        val user = credentials.getCachedCredentials()
        val apiKey = credentials.getCachedApiKey()

        if (user == null || apiKey.isNullOrBlank()) {
            return false
        }

        dogfoodingApp.initializeStreamVideo(
            AuthCredentialsProvider(
                apiKey = apiKey,
                user = user
            ),
            loggingLevel = LoggingLevel.NONE
        )
        return true
    }
}

internal const val API_KEY = "us83cfwuhy8n"

val Context.dogfoodingApp get() = applicationContext as DogfoodingApp
