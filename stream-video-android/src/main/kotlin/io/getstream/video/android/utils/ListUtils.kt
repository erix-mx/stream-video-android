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

package io.getstream.video.android.utils

public inline fun <T> List<T>.update(condition: (T) -> Boolean, transformer: (T) -> T): List<T> {
    val itemIndex = this.indexOfFirst(condition)

    val mutableList = toMutableList()
    val updatedItem = transformer(this[itemIndex])

    mutableList.removeAt(itemIndex)
    mutableList.add(itemIndex, updatedItem)

    return mutableList
}

public inline fun <T> List<T>.updateAll(transformer: (T) -> T): List<T> {
    val mutableList = toMutableList()

    return mutableList.map(transformer)
}