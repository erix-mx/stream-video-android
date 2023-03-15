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

package io.getstream.video.android.core.model

import org.openapitools.client.models.SendReactionRequest

/**
 * Represents the information about a reaction to be sent.
 *
 * @param type The type of reaction.
 * @param emoji Code of the emoji, if applicable.
 * @param custom Custom extra data to enrich the reaction.
 */
public data class SendReactionData(
    public val type: String,
    public val emoji: String? = null,
    public val custom: Map<String, Any>? = null
)

internal fun SendReactionData.toRequest(): SendReactionRequest {
    return SendReactionRequest(
        type = type,
        custom = custom,
        emojiCode = emoji
    )
}