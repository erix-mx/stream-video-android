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

@file:OptIn(ExperimentalSerializationApi::class)

package io.getstream.video.android.datastore.serializer

import androidx.datastore.core.Serializer
import io.getstream.video.android.datastore.model.StreamUserPreferences
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.InputStream
import java.io.OutputStream

internal class UserSerializer : Serializer<StreamUserPreferences?> {

    override val defaultValue: StreamUserPreferences? = null

    override suspend fun readFrom(input: InputStream): StreamUserPreferences? {
        val userInput = input.readBytes()
        if (userInput.isEmpty()) return null
        return ProtoBuf.decodeFromByteArray(StreamUserPreferences.serializer(), userInput)
    }

    override suspend fun writeTo(t: StreamUserPreferences?, output: OutputStream) {
        val userPreferences = t ?: return
        val byteArray = ProtoBuf.encodeToByteArray(StreamUserPreferences.serializer(), userPreferences)

        @Suppress("BlockingMethodInNonBlockingContext")
        output.write(byteArray)
    }
}