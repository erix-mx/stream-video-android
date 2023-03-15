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

import com.squareup.moshi.Json

/**
 * *
 * @param createdAt Date/time of creation
 * @param custom Custom member response data
 * @param duration * @param role * @param updatedAt Date/time of the last update
 * @param user * @param userId * @param deletedAt Date/time of deletion
 */

data class MemberResponse(

    /* Date/time of creation */
    @Json(name = "created_at")
    val createdAt: java.time.OffsetDateTime,

    /* Custom member response data */
    @Json(name = "custom")
    val custom: kotlin.collections.Map<kotlin.String, kotlin.Any>,

    @Json(name = "duration")
    val duration: kotlin.String,

    @Json(name = "role")
    val role: kotlin.String,

    /* Date/time of the last update */
    @Json(name = "updated_at")
    val updatedAt: java.time.OffsetDateTime,

    @Json(name = "user")
    val user: UserResponse,

    @Json(name = "user_id")
    val userId: kotlin.String,

    /* Date/time of deletion */
    @Json(name = "deleted_at")
    val deletedAt: java.time.OffsetDateTime? = null

)