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

package io.getstream.video.android.core.events

import io.getstream.video.android.core.model.CallDetails
import io.getstream.video.android.core.model.CallInfo
import io.getstream.video.android.core.model.CallUser
import io.getstream.video.android.core.model.StreamCallCid
import io.getstream.video.android.core.model.User
import java.util.Date

/**
 * Represents the events coming in from the socket.
 */
public sealed class VideoEvent : java.io.Serializable

/**
 * Triggered when a user gets connected to the WS.
 */
public data class ConnectedEvent(
    val clientId: String,
) : VideoEvent()

/**
 * Sent periodically by the server to keep the connection alive.
 */
public data class HealthCheckEvent(
    val clientId: String,
) : VideoEvent()

/**
 * Sent when someone creates a call and invites another person to participate.
 */
public data class CallCreatedEvent(
    val callCid: String,
    val ringing: Boolean,
    val users: Map<String, CallUser>,
    val callInfo: CallInfo,
    val callDetails: CallDetails,
) : VideoEvent()

/**
 * Sent when a call gets updated.
 */
public data class CallUpdatedEvent(
    val callCid: String,
    val capabilitiesByRole: Map<String, List<String>>,
    val info: CallInfo,
    val ownCapabilities: List<String>
) : VideoEvent()

/**
 * Sent when a calls gets ended.
 */
public data class CallEndedEvent(
    val callCid: String,
    val endedByUser: User?
) : VideoEvent()

/**
 * Sent when call members get updated.
 */
public data class CallMembersUpdatedEvent(
    val callCid: String,
    val users: Map<String, CallUser>,
    val info: CallInfo,
    val details: CallDetails
) : VideoEvent()

/**
 * Sent when call members get updated.
 */
public data class CallMembersDeletedEvent(
    val callCid: String,
    val users: Map<String, CallUser>,
    val info: CallInfo,
    val details: CallDetails
) : VideoEvent()

public data class CallAcceptedEvent(
    val callCid: String,
    val sentByUserId: String,
) : VideoEvent()

public data class CallRejectedEvent(
    val callCid: String,
    val user: User,
    val updatedAt: Date
) : VideoEvent()

public data class CallCancelledEvent(
    val callCid: String,
    val sentByUserId: String,
) : VideoEvent()

public data class CustomEvent(
    val cid: StreamCallCid?,
    val sentByUser: User?,
    val custom: Map<String, Any>?,
) : VideoEvent()

public data class BlockedUserEvent(
    val cid: StreamCallCid?,
    val type: String,
    val userId: String
) : VideoEvent()

public data class UnblockedUserEvent(
    val cid: StreamCallCid?,
    val type: String,
    val userId: String
) : VideoEvent()

public data class RecordingStartedEvent(
    val cid: StreamCallCid?,
    val type: String
) : VideoEvent()

public data class RecordingStoppedEvent(
    val cid: StreamCallCid?,
    val type: String
) : VideoEvent()

public data class PermissionRequestEvent(
    val cid: StreamCallCid?,
    val type: String,
    val permissions: List<String>,
    val user: User
) : VideoEvent()

public data class UpdatedCallPermissionsEvent(
    val cid: StreamCallCid?,
    val type: String,
    val ownCapabilities: List<String>,
    val user: User
) : VideoEvent()

public object UnknownEvent : VideoEvent()