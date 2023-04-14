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

package io.getstream.video.android.core

import com.google.common.truth.Truth.assertThat
import io.getstream.log.taggedLogger
import io.getstream.result.Error
import io.getstream.video.android.core.errors.VideoErrorCode
import io.getstream.video.android.core.model.QueryCallsData
import io.getstream.video.android.core.model.User
import io.getstream.video.android.core.model.UserType
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.openapitools.client.models.ConnectedEvent
import org.openapitools.client.models.VideoEvent
import org.robolectric.RobolectricTestRunner
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@RunWith(RobolectricTestRunner::class)
class ClientAndAuthTest : TestBase() {

    private val logger by taggedLogger("Test:ClientAndAuthTest")

    @Test
    fun regularUser() = runTest {
        StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            testData.users["thierry"]!!,
            testData.tokens["thierry"]!!,
        ).build()
    }

    @Test
    fun anonymousUser() = runTest {
        StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            user = User(id = "anonymous", type = UserType.Anonymous)
        ).build()
    }

    @Test
    fun guestUser() = runTest {
        // we get the token from Stream's server in this case
        // the ID is generated, client side...
        // verify that we get the token
        // API call is getGuestUser or something like that
        StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            user = User(id = "guest", type = UserType.Guest)
        ).build()


    }

    @Test
    fun subscribeToAllEvents() = runTest {

        val client = StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            user = User(id = "guest", type = UserType.Guest)
        ).build()
        val sub = client.subscribe { event: VideoEvent ->
            logger.d { event.toString() }
        }
        sub.dispose()
    }

    @Test
    fun subscribeToSpecificEvents() = runTest {

        val client = StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            user = User(id = "guest", type = UserType.Guest)
        ).build()
        // Subscribe for new message events
        val sub = client.subscribeFor<ConnectedEvent> { newMessageEvent ->
            logger.d { newMessageEvent.toString() }
        }
        sub.dispose()
    }

    @Test
    fun waitForWSConnection() = runTest {
        val client = StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            testData.users["thierry"]!!,
            testData.tokens["thierry"]!!,
        ).build()
        assertThat(client.state.connection.value).isEqualTo(ConnectionState.PreConnect)
        val clientImpl = client as StreamVideoImpl
        println("a")
        val connectResultDeferred = clientImpl.connectAsync()
        println("b")
        val connectResult = connectResultDeferred.await()
        delay(100L)
        assertThat(client.state.connection.value).isEqualTo(ConnectionState.Connected)
        println("reached")
    }

    @Test
    fun testInvalidAPIKey() = runTest {
        StreamVideoBuilder(
            context = context,
            apiKey = "notvalid",
            geo = GEO.GlobalEdgeNetwork,
            testData.users["thierry"]!!,
            testData.tokens["thierry"]!!,
        ).build()
    }

    @Test
    fun `test an expired token, no provider set`() = runTest {
        val client = StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            testData.users["thierry"]!!,
            testData.expiredToken,
        ).build()

        val result = client.call("default", "123").create()
        assertError(result)
        result.onError {
            it as Error.NetworkError
            assertThat(it.serverErrorCode).isEqualTo(VideoErrorCode.TOKEN_EXPIRED.code)
        }
    }

    @Test
    fun `test an expired token, with token provider set`() = runTest {
        val client = StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            testData.users["thierry"]!!,
            testData.expiredToken,
            tokenProvider = { error ->
                testData.tokens["thierry"]!!
            }
        ).build()

        val result = client.call("default", "123").create()
        assertSuccess(result)
    }

    @Test
    fun testEmptyAPIKey() = runTest {
//        assertFailsWith<java.lang.IllegalArgumentException> {
//            StreamVideoBuilder(
//                context = context,
//                apiKey = "",
//                geo = GEO.GlobalEdgeNetwork,
//                testData.users["thierry"]!!,
//                testData.tokens["thierry"]!!,
//            ).build()
//        }
    }

    @Test
    fun testWaitingForConnection() = runTest {
        // often you'll want to run the connection task in the background and not wait for it
        val client = StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            testData.users["thierry"]!!,
            testData.tokens["thierry"]!!,
        ).build()
        val clientImpl = client as StreamVideoImpl
        client.subscribe {
            println(it)
        }
        val deferred = clientImpl.connectAsync()
        deferred.join()
    }

    @Test
    fun `join a call without waiting for a connection`() = runTest {
        // often you'll want to run the connection task in the background and not wait for it
        val client = StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            testData.users["thierry"]!!,
            testData.tokens["thierry"]!!,
        ).build()
        val clientImpl = client as StreamVideoImpl
        val call = client.call("default", randomUUID())
        val callCreateResult = call.create()
        assertSuccess(callCreateResult)
        // wonder if this will work, no coordinator connection
        val callJoined = call.join()
    }

    @Test
    fun testConnectionId() = runTest {
        // all requests should have a connection id
        // the connection id comes from the websocket
        // TODO:
        // - you shouldn't immediately connect to the WS
        // - maybe a manual connect step is best
        // - maybe it doesn't wait for WS
        // - there is no .connect on android, when should it connect?

        val client = StreamVideoBuilder(
            context = context,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            testData.users["thierry"]!!,
            testData.tokens["thierry"]!!,
        ).build()
        // client.connect()
        val filters = mutableMapOf("active" to true)
        client.joinCall("default", "123")

        val result = client.queryCalls(QueryCallsData(filters))
        assert(result.isSuccess)
    }
}
