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

package io.getstream.video.android.model.mapper

import io.getstream.video.android.model.StreamCallCid
import io.getstream.video.android.model.StreamCallId
import io.getstream.video.android.model.StreamCallType

private const val CID_SIZE = 2

/**
 * Parses [StreamCallCid] of call to callType and channelId.
 *
 * @return Pair<StreamCallType, StreamCallId> Pair with callType and channelId.
 * @throws IllegalStateException Throws an exception if format of cid is incorrect.
 *
 * @see StreamCallCid
 * @see StreamCallType
 * @see StreamCallId
 */
@Throws(IllegalStateException::class)
public fun StreamCallCid.toTypeAndId(): Pair<StreamCallType, StreamCallId> {
    check(isNotEmpty()) { "StreamCallCid can not be empty" }
    check(':' in this) {
        "StreamCallCid needs to be in the format StreamCallType:StreamCallId. For example, default:123"
    }
    return split(":").takeIf { it.size >= CID_SIZE }?.let { it.first() to it.last() }
        ?: error("unexpected StreamCallCid format: $this")
}
