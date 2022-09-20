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

package io.getstream.video.android.webrtc

import io.getstream.video.android.audio.AudioDevice
import io.getstream.video.android.model.Room

public interface WebRTCClient {

    public fun clear()

    public fun startCall(sessionId: String, shouldPublish: Boolean): Room

    public fun joinCall(sessionId: String, shouldPublish: Boolean): Room

    public fun startCapturingLocalVideo(position: Int)

    public fun setCameraEnabled(isEnabled: Boolean)

    public fun setMicrophoneEnabled(isEnabled: Boolean)

    public fun flipCamera()

    public fun getAudioDevices(): List<AudioDevice>

    public fun selectAudioDevice(device: AudioDevice)
}