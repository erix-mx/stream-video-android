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

package io.getstream.video.android.core.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import io.getstream.video.android.core.model.Call
import org.webrtc.VideoTrack
import stream.video.sfu.models.TrackType

public class ParticipantItemView : TextureViewRenderer {

    private var track: VideoTrack? = null
    public var isInitialized: Boolean = false
        private set

    public constructor(context: Context) : super(context)
    public constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    public fun bindTrack(track: VideoTrack) {
        this.track = track

        track.addSink(this)
    }

    public fun cleanUp() {
        this.track?.removeSink(this)
        this.track = null
    }

    public fun initialize(call: Call, streamId: String, onRender: (View) -> Unit = {}) {
        call.initRenderer(this, streamId, TrackType.TRACK_TYPE_VIDEO, onRender)
        this.isInitialized = true
    }
}