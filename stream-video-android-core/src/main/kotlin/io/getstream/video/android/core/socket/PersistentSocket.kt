package io.getstream.video.android.core.socket

import com.squareup.moshi.JsonAdapter
import io.getstream.log.taggedLogger
import io.getstream.video.android.core.call.signal.socket.RTCEventMapper
import io.getstream.video.android.core.dispatchers.DispatcherProvider
import io.getstream.video.android.core.events.JoinCallResponseEvent
import io.getstream.video.android.core.internal.network.NetworkStateProvider
import io.getstream.video.android.core.socket.internal.EventType
import io.getstream.video.android.core.socket.internal.HealthMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.*
import okio.ByteString
import org.openapitools.client.infrastructure.Serializer
import org.openapitools.client.models.ConnectedEvent
import org.openapitools.client.models.HealthCheckEvent
import org.openapitools.client.models.VideoEvent
import org.openapitools.client.models.WSEvent
import stream.video.sfu.event.HealthCheckRequest
import stream.video.sfu.event.SfuEvent
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * PersistentSocket architecture
 *
 * - Healthmonitor that sends a ping every 30 seconds
 * - Automatically reconnects if it encounters a temp failure
 * - Raises the error if there is a permanent failure
 * - Flow to avoid concurrency related bugs
 * - Ability to wait till the socket is connected (important to prevent race conditions)
 */
open class PersistentSocket<T>(
    /** The URL to connect to */
    private val url: String,
    /** Inject your http client */
    private val httpClient: OkHttpClient,

    /** The token for authentication */
    private val token: String,
    /** Inject your network state provider */
    private val networkStateProvider: NetworkStateProvider,
    /** Set the scope everything should run in */
    private val scope : CoroutineScope = CoroutineScope(DispatcherProvider.IO)
) : WebSocketListener() {
    internal open val logger by taggedLogger("PersistentSocket")

    /** flow with all the events, listen to this */
    val events = MutableSharedFlow<VideoEvent>()
    /** flow with temporary and permanent errors */
    val errors = MutableSharedFlow<Throwable>()

    val _connectionState = MutableStateFlow<SocketState>(SocketState.NotConnected)
    /** the current connection state of the socket */
    val connectionState: StateFlow<SocketState> = _connectionState

    /** the connection id */
    var connectionId: String = ""

    /** Continuation if the socket successfully connected and we've authenticated */
    lateinit var connected : Continuation<T>

    internal lateinit var socket: WebSocket

    // prevent us from resuming the continuation twice
    private var continuationCompleted: Boolean = false
    // we don't raise errors if you're closing the connection yourself
    private var closedByClient: Boolean = false

    /**
     * Connect the socket, authenticate, start the healthmonitor and see if the network is online
     */
    suspend fun  connect() = suspendCoroutine<T> { connectedContinuation ->
        logger.i { "[connect]" }
        connected = connectedContinuation

        _connectionState.value = SocketState.Connecting
        // step 1 create the socket
        socket = createSocket()

        println("1")
        scope.launch {
            // step 2 authenticate the user/call etc
            println("2")
            authenticate()
            println("3")
            // step 3 monitor for health every 30 seconds
            healthMonitor.start()
            println("4")
            // also monitor if we are offline/online
            networkStateProvider.subscribe(networkStateListener)
            println("5")

        }
    }

    /**
     * Disconnect the socket
     */
    fun disconnect() {
        logger.i { "[disconnect]" }
        closedByClient = true
        continuationCompleted = false
        _connectionState.value = SocketState.DisconnectedByRequest
        socket.close(CODE_CLOSE_SOCKET_FROM_CLIENT, "Connection close by client")
        connectionId = ""
        healthMonitor.stop()
        networkStateProvider.unsubscribe(networkStateListener)
    }

    /**
     * Increment the reconnection attempts, disconnect and reconnect
     */
    suspend fun reconnect(timeout: Long = 500) {
        logger.i { "[reconnect] reconnectionAttempts: $reconnectionAttempts" }
        reconnectionAttempts++
        disconnect()
        delay(timeout)
        connect()
    }

    open fun authenticate() {

    }

    private val networkStateListener = object : NetworkStateProvider.NetworkStateListener {
        override fun onConnected() {
            val state = connectionState.value
            logger.i { "[onNetworkConnected] state: $state" }
            if (state is SocketState.DisconnectedTemporarily || state == SocketState.NetworkDisconnected) {
                scope.launch {
                    reconnect()
                }
            }
        }

        override fun onDisconnected() {
            logger.i { "[onNetworkDisconnected] state: $connectionState.value" }
            if (connectionState.value is SocketState.Connected || connectionState.value is SocketState.Connecting) {
                _connectionState.value = SocketState.NetworkDisconnected
            }
        }
    }

    private var reconnectionAttempts = 0

    fun createSocket(): WebSocket {
        logger.d { "[createSocket] url: $url" }

        val request = Request.Builder()
            .url(url)
            .addHeader("Connection", "Upgrade")
            .addHeader("Upgrade", "websocket")
            .build()

        return httpClient.newWebSocket(request, this)
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        logger.d { "[onOpen] response: $response" }



    }

    /** Invoked when a text (type `0x1`) message has been received. */
    override fun onMessage(webSocket: WebSocket, text: String) {
        logger.d { "[onMessage] text: $text " }

        scope.launch {
            // parse the message
            val jsonAdapter: JsonAdapter<VideoEvent> = Serializer.moshi.adapter(VideoEvent::class.java)
            var processedEvent = jsonAdapter.fromJson(text)

            if (processedEvent is ConnectedEvent) {
                connectionId = processedEvent.connectionId
                _connectionState.value = SocketState.Connected(processedEvent)
                if (!continuationCompleted) {
                    connected.resume(processedEvent as T)
                    continuationCompleted = true
                }

            } else if (processedEvent is JoinCallResponseEvent) {
                if (!continuationCompleted) {
                    connected.resume(processedEvent as T)
                    continuationCompleted = true
                }
            }

            logger.d { "parsed event $processedEvent" }

            // emit the message
            events.emit(processedEvent)
        }
    }

    /** Invoked when a binary (type `0x2`) message has been received. */
    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        logger.d { "[onMessage] bytes: $bytes" }
        val byteBuffer = bytes.asByteBuffer()
        val byteArray = ByteArray(byteBuffer.capacity())
        byteBuffer.get(byteArray)
        scope.launch {
            try {
                val rawEvent = SfuEvent.ADAPTER.decode(byteArray)
                logger.v { "[onMessage] rawEvent: $rawEvent" }
                val message = RTCEventMapper.mapEvent(rawEvent)
                events.emit(message)
            } catch (error: Throwable) {
                logger.e { "[onMessage] failed: $error" }
                emitError(error)
            }
        }
    }

    internal fun emitError(error: Throwable) {
        scope.launch {
            errors.emit(error)
            error.printStackTrace()
        }
    }

    /**
     * Invoked when the remote peer has indicated that no more incoming messages will be transmitted.
     */
    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        logger.d { "[onClosing] code: $code, reason: $reason" }
    }

    /**
     * Invoked when both peers have indicated that no more messages will be transmitted and the
     * connection has been successfully released. No further calls to this listener will be made.
     */
    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        logger.d { "[onClosed] code: $code, reason: $reason" }
        if (code == CODE_CLOSE_SOCKET_FROM_CLIENT) {
            closedByClient = true
        } else {
            // Treat as failure and reconnect, socket shouldn't be closed by server
            emitError(IllegalStateException("socket closed by server, this shouldnt happen"))
        }
    }


    /**
     * Invoked when a web socket has been closed due to an error reading from or writing to the
     * network. Both outgoing and incoming messages may have been lost. No further calls to this
     * listener will be made.
     */
    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        logger.d { "[onFailure] t: $t, response: $response" }
        if (!continuationCompleted) {
            connected.resumeWithException(t)
            continuationCompleted = true
        }

        emitError(t)
    }

    private val healthMonitor = HealthMonitor(object : HealthMonitor.HealthCallback {
        override fun reconnect() {
            val state = connectionState.value
            if (state is SocketState.DisconnectedTemporarily) {
                scope.launch {
                    this@PersistentSocket.reconnect()
                }
            }
        }

        override fun check() {
            val state = connectionState.value
            (state as? SocketState.Connected)?.let {
                val healthCheckRequest = HealthCheckRequest()
                socket.send(healthCheckRequest.encodeByteString())
            }
        }
    })

    internal companion object {
        internal const val CODE_CLOSE_SOCKET_FROM_CLIENT = 1000
    }


}