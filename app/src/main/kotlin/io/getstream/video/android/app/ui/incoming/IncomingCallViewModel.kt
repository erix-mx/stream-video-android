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

package io.getstream.video.android.app.ui.incoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.getstream.logging.StreamLog
import io.getstream.video.android.StreamCalls
import io.getstream.video.android.model.CallInput
import io.getstream.video.android.model.IncomingCallData
import io.getstream.video.android.model.JoinedCall
import io.getstream.video.android.model.callId
import io.getstream.video.android.router.StreamRouter
import io.getstream.video.android.utils.Failure
import io.getstream.video.android.utils.Result
import io.getstream.video.android.utils.Success
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import stream.video.coordinator.client_v1_rpc.UserEventType

class IncomingCallViewModel(
    private val streamCalls: StreamCalls,
    private val streamRouter: StreamRouter,
    private val callData: IncomingCallData
) : ViewModel() {

    private val logger = StreamLog.getLogger("Call:Incoming-VM")

    init {
        scheduleTimer()
    }

    private fun scheduleTimer() {
        viewModelScope.launch {
            delay(timeMillis = 10_000) // TODO - we'll have to provide some config here
        }
    }

    fun acceptCall() {
        val data = callData
        val callType = data.callInfo.type
        val callId = data.callInfo.callId
        logger.d { "[acceptCall] callType: $callType, callId: $callId" }

        viewModelScope.launch {
            val joinResult = streamCalls.joinCall(callType, callId)
            logger.v { "[acceptCall] join result: $joinResult" }

            onJoinResult(joinResult)
        }
    }

    private suspend fun onJoinResult(joinResult: Result<JoinedCall>) {
        when (joinResult) {
            is Success -> {
                val joinData = joinResult.data

                streamCalls.sendEvent(
                    joinData.call.id,
                    joinData.call.type,
                    UserEventType.USER_EVENT_TYPE_ACCEPTED_CALL
                )

                streamRouter.navigateToCall(
                    CallInput(
                        callId = joinData.call.id,
                        callUrl = joinData.callUrl,
                        userToken = joinData.userToken,
                        iceServers = joinData.iceServers
                    )
                )
            }
            is Failure -> {
                declineCall()
            }
        }
    }

    fun declineCall() {
        val data = callData
        viewModelScope.launch {
            val result = streamCalls.sendEvent(
                data.callInfo.callId,
                data.callInfo.type,
                UserEventType.USER_EVENT_TYPE_REJECTED_CALL
            )
            logger.d { "[declineCall] result: $result" }

            streamCalls.clearCallState()
            streamRouter.onCallFailed(reason = "Call rejected!")
        }
    }
}

