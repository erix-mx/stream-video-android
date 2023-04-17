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

import android.content.Context
import io.getstream.video.android.core.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.openapitools.client.models.CallCreatedEvent
import org.openapitools.client.models.ConnectedEvent
import org.openapitools.client.models.VideoEvent

sealed interface ConnectionState {
    object Idle : ConnectionState
    object PreConnect : ConnectionState
    object Loading : ConnectionState
    object Connected : ConnectionState
    object Reconnecting : ConnectionState
    object Disconnected : ConnectionState
    class Failed(error: Error) : ConnectionState
}

sealed class RingingState {
    object Idle : RingingState()
    data class Incoming(public val acceptedByMe: Boolean) : RingingState()
    data class Outgoing(public val acceptedByCallee: Boolean) : RingingState()
    object Active : RingingState()
    object RejectedByAll : RingingState()
    object TimeoutNoAnswer : RingingState()
}

class ClientState(client: StreamVideo) {
    /**
     * Current user object
     */
    private val _user: MutableStateFlow<User?> = MutableStateFlow(client.user)
    public val user: StateFlow<User?> = _user

    /**
     * connectionState shows if we've established a connection with the coordinator
     */
    private val _connection: MutableStateFlow<ConnectionState> =
        MutableStateFlow(ConnectionState.PreConnect)
    public val connection: StateFlow<ConnectionState> = _connection

    /**
     * Incoming call. True when we receive an event or notification with an incoming call
     */
    private val _ringingCall: MutableStateFlow<Call?> = MutableStateFlow(null)
    public val ringingCall: StateFlow<Call?> = _ringingCall

    /**
     * Active call. The call that you've currently joined
     */
    private val _activeCall: MutableStateFlow<Call?> = MutableStateFlow(null)
    public val activeCall: StateFlow<Call?> = _activeCall

    internal val clientImpl = client as StreamVideoImpl

    /**
     * Handles the events for the client state.
     * Most event logic happens in the Call instead of the client
     */

    fun handleEvent(event: VideoEvent) {
        // mark connected
        if (event is ConnectedEvent) {

            _connection.value = ConnectionState.Connected
        } else if (event is CallCreatedEvent) {
            // what's the right thing to do here?
            // if it's ringing we add it

            // get or create the call and update it
            val (type, id) = event.callCid.split(":")
            val call = clientImpl.call(type, id)
            call.state.updateFromEvent(event)

            if (event.ringing) {
                _ringingCall.value = call
            }
        }
    }

    fun setActiveCall(call: Call) {
        this._activeCall.value = call
    }

    fun removeActiveCall() {
        this._activeCall.value = null
    }

    fun addRingingCall(call: Call) {
        // TODO: behaviour if you are already in a call
        _ringingCall.value = call
    }
}

public fun ConnectionState.formatAsTitle(context: Context): String = when (this) {
    ConnectionState.Idle -> "Idle"
    ConnectionState.PreConnect -> "PreConnect"
    ConnectionState.Loading -> "Loading"
    ConnectionState.Connected -> "Connected"
    ConnectionState.Reconnecting -> "Reconnecting"
    ConnectionState.Disconnected -> "Disconnected"
    is ConnectionState.Failed -> "Failed"
}
