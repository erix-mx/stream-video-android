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

package io.getstream.video.android.call.signal.socket

import io.getstream.video.android.socket.Socket
import io.getstream.video.android.utils.prepareUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

internal class SignalSocketFactory(
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build(),
) {

    /**
     * Creates a socket that's used to observe events from the server.
     *
     * @param eventsParser Parser used to transform events.
     * @param connectionConf Configuration used to build the socket.
     */
    fun createSocket(eventsParser: SignalEventsParser, connectionConf: ConnectionConf): Socket {
        val url = prepareUrl(connectionConf.endpoint)
        val request = Request
            .Builder()
            .url(url)
            .build()

        val webSocket = httpClient.newWebSocket(request, eventsParser)
        return Socket(webSocket)
    }

    /**
     * Describes the configuration used to build a socket.
     *
     * @property endpoint The URL endpoint to connect the socket to.
     */
    internal class ConnectionConf(val endpoint: String) {
        var isReconnection: Boolean = false
            private set

        internal fun asReconnectionConf(): ConnectionConf = this.also { isReconnection = true }
    }
}