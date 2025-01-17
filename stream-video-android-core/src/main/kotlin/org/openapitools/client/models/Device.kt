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





import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import org.openapitools.client.infrastructure.Serializer

/**
 *
 *
 * @param createdAt Date/time of creation
 * @param id
 * @param pushProvider
 * @param disabled Whether device is disabled or not
 * @param disabledReason Reason explaining why device had been disabled
 * @param pushProviderName
 * @param voip When true the token is for Apple VoIP push notifications
 */


data class Device (

    /* Date/time of creation */
    @Json(name = "created_at")
    val createdAt: org.threeten.bp.OffsetDateTime,

    @Json(name = "id")
    val id: kotlin.String,

    @Json(name = "push_provider")
    val pushProvider: kotlin.String,

    /* Whether device is disabled or not */
    @Json(name = "disabled")
    val disabled: kotlin.Boolean? = null,

    /* Reason explaining why device had been disabled */
    @Json(name = "disabled_reason")
    val disabledReason: kotlin.String? = null,

    @Json(name = "push_provider_name")
    val pushProviderName: kotlin.String? = null,

    /* When true the token is for Apple VoIP push notifications */
    @Json(name = "voip")
    val voip: kotlin.Boolean? = null

)
