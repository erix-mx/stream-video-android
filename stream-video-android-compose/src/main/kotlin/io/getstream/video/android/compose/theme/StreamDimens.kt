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

package io.getstream.video.android.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Contains all the dimens we provide for our components.
 *
 * @param callAvatarSize The size of channel avatar.
 * @param headerElevation The elevation of the headers, such as the ones appearing on the Channel or Message screens.
 */
@Immutable
public data class StreamDimens(
    public val callAvatarSize: Dp,
    public val singleAvatarSize: Dp,
    public val headerElevation: Dp,
    public val largeButtonSize: Dp,
    public val mediumButtonSize: Dp,
    public val smallButtonSize: Dp,
    public val topAppbarHeightSize: Dp,
    public val avatarAppbarPadding: Dp,
    public val singleAvatarAppbarPadding: Dp,
    public val participantsTextPadding: Dp,
    public val directCallUserNameTextSize: TextUnit,
    public val groupCallUserNameTextSize: TextUnit,
    public val onCallStatusTextSize: TextUnit,
    public val topAppbarTextSize: TextUnit,
    public val onCallStatusTextAlpha: Float,
    public val buttonToggleOnAlpha: Float,
    public val buttonToggleOffAlpha: Float,
) {
    public companion object {
        /**
         * Builds the default dimensions for our theme.
         *
         * @return A [StreamDimens] instance holding our default dimensions.
         */
        public fun defaultDimens(): StreamDimens = StreamDimens(
            callAvatarSize = 80.dp,
            singleAvatarSize = 160.dp,
            headerElevation = 4.dp,
            largeButtonSize = 80.dp,
            mediumButtonSize = 64.dp,
            smallButtonSize = 32.dp,
            topAppbarHeightSize = 64.dp,
            avatarAppbarPadding = 100.dp,
            singleAvatarAppbarPadding = 20.dp,
            participantsTextPadding = 65.dp,
            topAppbarTextSize = 17.sp,
            directCallUserNameTextSize = 34.sp,
            groupCallUserNameTextSize = 24.sp,
            onCallStatusTextSize = 20.sp,
            onCallStatusTextAlpha = 0.6f,
            buttonToggleOnAlpha = 0.4f,
            buttonToggleOffAlpha = 1.0f,
        )
    }
}