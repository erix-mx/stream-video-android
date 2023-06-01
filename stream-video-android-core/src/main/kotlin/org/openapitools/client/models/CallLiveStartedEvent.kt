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

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.models

import org.openapitools.client.models.CallResponse




import com.squareup.moshi.Json

/**
 * This event is sent when a call is started. Clients receiving this event should start the call.
 *
 * @param call
 * @param callCid
 * @param createdAt
 * @param type The type of event: \"call.live_started\" in this case
 */


data class CallLiveStartedEvent (

    @Json(name = "call")
    val call: CallResponse,

    @Json(name = "call_cid")
    val callCid: kotlin.String,

    @Json(name = "created_at")
    val createdAt: org.threeten.bp.OffsetDateTime,

    /* The type of event: \"call.live_started\" in this case */
    @Json(name = "type")
    val type: kotlin.String = "call.live_started"

) : VideoEvent(), WSCallEvent{

    override fun getCallCID(): String {
        return callCid
    }

    override fun getEventType(): String {
        return type
    }
}