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

package io.getstream.video.android.call.state

/**
 * Represents various actions users can take while in a call.
 */
public sealed class CallAction

/**
 * Action to toggle if the speakerphone is on or off.
 */
public data class ToggleSpeakerphone(
    val isEnabled: Boolean
) : CallAction()

/**
 * Action to toggle if the camera is on or off.
 */
public data class ToggleCamera(
    val isEnabled: Boolean
) : CallAction()

/**
 * Action to toggle if the microphone is on or off.
 */
public data class ToggleMicrophone(
    val isEnabled: Boolean
) : CallAction()

/**
 * Action to flip the active camera.
 */
public object FlipCamera : CallAction()

/**
 * Action to leave the call.
 */
public object LeaveCall : CallAction()

/**
 * Custom action used to handle any custom behavior with the given [data], such as opening chat,
 * inviting people, sharing the screen and more.
 */
public data class CustomAction(
    val data: Map<Any, Any> = emptyMap()
) : CallAction()