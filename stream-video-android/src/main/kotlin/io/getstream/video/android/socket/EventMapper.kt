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

package io.getstream.video.android.socket

import io.getstream.video.android.events.CallAcceptedEvent
import io.getstream.video.android.events.CallCanceledEvent
import io.getstream.video.android.events.CallCreatedEvent
import io.getstream.video.android.events.CallEndedEvent
import io.getstream.video.android.events.CallMembersDeletedEvent
import io.getstream.video.android.events.CallMembersUpdatedEvent
import io.getstream.video.android.events.CallRejectedEvent
import io.getstream.video.android.events.CallUpdatedEvent
import io.getstream.video.android.events.HealthCheckEvent
import io.getstream.video.android.events.UnknownEvent
import io.getstream.video.android.events.VideoEvent
import io.getstream.video.android.model.toCallDetails
import io.getstream.video.android.model.toCallInfo
import io.getstream.video.android.model.toCallUsers
import stream.video.coordinator.client_v1_rpc.WebsocketEvent

internal object EventMapper {

    /**
     * Maps [WebsocketEvent]s to our [VideoEvent] that corresponds to the data.
     *
     * @param socketEvent The event we received through the WebSocket.
     * @return [VideoEvent] representation of the data.
     */
    internal fun mapEvent(socketEvent: WebsocketEvent): VideoEvent = when {
        socketEvent.healthcheck != null -> with(socketEvent.healthcheck) {
            HealthCheckEvent(userId = user_id, clientId = client_id)
        }

        socketEvent.call_created != null -> with(socketEvent.call_created) {
            CallCreatedEvent(
                callCid = call!!.call_cid,
                ringing = ringing,
                users = socketEvent.users.toCallUsers(),
                info = call.toCallInfo(),
                details = call_details.toCallDetails(),
            )
        }

        socketEvent.call_updated != null -> with(socketEvent.call_updated) {
            CallUpdatedEvent(
                callCid = call!!.call_cid,
                users = socketEvent.users.toCallUsers(),
                info = call.toCallInfo(),
                details = call_details.toCallDetails(),
            )
        }

        socketEvent.call_ended != null -> with(socketEvent.call_ended) {
            CallEndedEvent(
                callCid = call!!.call_cid,
                users = socketEvent.users.toCallUsers(),
                info = call.toCallInfo(),
                details = call_details.toCallDetails(),
            )
        }

        socketEvent.call_members_updated != null -> with(socketEvent.call_members_updated) {
            CallMembersUpdatedEvent(
                callCid = call!!.call_cid,
                users = socketEvent.users.toCallUsers(),
                info = call.toCallInfo(),
                details = call_details.toCallDetails(),
            )
        }

        socketEvent.call_members_deleted != null -> with(socketEvent.call_members_deleted) {
            CallMembersDeletedEvent(
                callCid = call!!.call_cid,
                users = socketEvent.users.toCallUsers(),
                info = call.toCallInfo(),
                details = call_details.toCallDetails(),
            )
        }

        socketEvent.call_accepted != null -> with(socketEvent.call_accepted) {
            CallAcceptedEvent(
                callCid = call!!.call_cid,
                sentByUserId = sender_user_id,
                users = socketEvent.users.toCallUsers(),
                info = call.toCallInfo(),
                details = call_details.toCallDetails(),
            )
        }

        socketEvent.call_rejected != null -> with(socketEvent.call_rejected) {
            CallRejectedEvent(
                callCid = call!!.call_cid,
                sentByUserId = sender_user_id,
                users = socketEvent.users.toCallUsers(),
                info = call.toCallInfo(),
                details = call_details.toCallDetails(),
            )
        }

        socketEvent.call_cancelled != null -> with(socketEvent.call_cancelled) {
            CallCanceledEvent(
                callCid = call!!.call_cid,
                sentByUserId = sender_user_id,
                users = socketEvent.users.toCallUsers(),
                info = call.toCallInfo(),
                details = call_details.toCallDetails(),
            )
        }

        else -> UnknownEvent
    }
}
