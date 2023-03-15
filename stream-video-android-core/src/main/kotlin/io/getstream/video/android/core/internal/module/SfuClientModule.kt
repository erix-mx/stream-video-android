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

package io.getstream.video.android.core.internal.module

import io.getstream.video.android.core.api.SignalServerService
import io.getstream.video.android.core.call.signal.SfuClient
import io.getstream.video.android.core.call.signal.SfuClientImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.wire.WireConverterFactory

internal class SfuClientModule(
    private val okHttpClient: OkHttpClient,
    private val signalUrl: String
) {

    private val signalRetrofitClient: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(WireConverterFactory.create())
            .baseUrl(signalUrl)
            .build()
    }

    internal val sfuClient: SfuClient by lazy {
        val service = signalRetrofitClient.create(SignalServerService::class.java)

        SfuClientImpl(service)
    }

    companion object {
        /**
         * Reusable instance of the module.
         */
        private var module: SfuClientModule? = null

        /**
         * Returns an instance of the [SfuClientModule]. If one doesn't exists, creates
         * the instance and then returns it.
         */
        internal fun getOrCreate(
            okHttpClient: OkHttpClient,
            signalUrl: String
        ): SfuClientModule {
            return module ?: synchronized(this) {
                module ?: SfuClientModule(okHttpClient, signalUrl).also {
                    module = it
                }
            }
        }
    }
}