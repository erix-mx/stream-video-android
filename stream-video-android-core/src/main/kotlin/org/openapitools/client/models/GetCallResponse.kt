/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.models

import org.openapitools.client.models.CallResponse
import org.openapitools.client.models.MemberResponse
import org.openapitools.client.models.UserResponse




import com.squareup.moshi.Json

/**
 * 
 *
 * @param blockedUsers 
 * @param call 
 * @param duration 
 * @param members 
 * @param membership 
 */


data class GetCallResponse (

    @Json(name = "blocked_users")
    val blockedUsers: kotlin.collections.List<UserResponse>,

    @Json(name = "call")
    val call: CallResponse,

    @Json(name = "duration")
    val duration: kotlin.String,

    @Json(name = "members")
    val members: kotlin.collections.List<MemberResponse>,

    @Json(name = "membership")
    val membership: MemberResponse? = null

)




