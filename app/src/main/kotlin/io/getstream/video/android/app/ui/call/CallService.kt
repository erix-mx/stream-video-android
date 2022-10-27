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

package io.getstream.video.android.app.ui.call

import android.content.Context
import io.getstream.video.android.StreamCalls
import io.getstream.video.android.app.videoApp
import io.getstream.video.android.service.StreamCallService

class CallService : StreamCallService() {

    override fun getStreamCalls(context: Context): StreamCalls = videoApp.streamCalls

    companion object {
        fun start(context: Context) = start<CallService>(context)
        fun stop(context: Context) = stop<CallService>(context)
    }
}