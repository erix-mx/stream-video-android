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

package io.getstream.video.android.model.state

import io.getstream.video.android.model.CallDetails
import io.getstream.video.android.model.CallInfo
import io.getstream.video.android.model.CallUser
import io.getstream.video.android.model.JoinedCall

public sealed interface StreamCallState {

    public object Idle : StreamCallState

    public data class Drop(
        val reason: DropReason
    ) : StreamCallState

    public data class Creating(
        val users: Map<String, CallUser>,
    ) : StreamCallState

    public interface Created : StreamCallState

    public data class Outgoing(
        val callId: String,
        val users: Map<String, CallUser>,
        val info: CallInfo,
        val details: CallDetails
    ) : Created

    public data class Incoming(
        val callId: String,
        val users: Map<String, CallUser>,
        val info: CallInfo,
        val details: CallDetails
    ) : Created

    public data class InCall(
        val joinedCall: JoinedCall
    ) : Created
}

public sealed class DropReason {
    public object Cancelled : DropReason()
    public object Timeout : DropReason()
}
