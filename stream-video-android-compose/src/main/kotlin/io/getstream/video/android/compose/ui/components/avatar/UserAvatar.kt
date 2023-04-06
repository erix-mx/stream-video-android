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

package io.getstream.video.android.compose.ui.components.avatar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.previews.ParticipantsProvider
import io.getstream.video.android.core.model.CallParticipantState
import io.getstream.video.android.core.model.User
import io.getstream.video.android.core.model.toUser
import io.getstream.video.android.core.utils.initials
import io.getstream.video.android.ui.common.R

/**
 * Represents the [User] avatar that's shown on the Messages screen or in headers of DMs.
 *
 * Based on the state within the [User], we either show an image or their initials.
 *
 * @param user The user whose avatar we want to show.
 * @param modifier Modifier for styling.
 * @param shape The shape of the avatar.
 * @param textStyle The [TextStyle] that will be used for the initials.
 * @param contentScale The scale option used for the content.
 * @param contentDescription The content description of the avatar.
 * @param initialsAvatarOffset The initials offset to apply to the avatar.
 * @param onClick The handler when the user clicks on the avatar.
 */
@Composable
public fun UserAvatar(
    user: User,
    modifier: Modifier = Modifier,
    shape: Shape = VideoTheme.shapes.avatar,
    textStyle: TextStyle = VideoTheme.typography.title3Bold,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    requestSize: IntSize = IntSize(DEFAULT_IMAGE_SIZE, DEFAULT_IMAGE_SIZE),
    @DrawableRes loadingPlaceholder: Int? = null,
    @DrawableRes previewPlaceholder: Int = R.drawable.ic_preview_avatar,
    initialsAvatarOffset: DpOffset = DpOffset(0.dp, 0.dp),
    onClick: (() -> Unit)? = null,
) {
    Box(modifier = modifier) {
        Avatar(
            modifier = Modifier.fillMaxSize(),
            imageUrl = user.imageUrl.orEmpty(),
            initials = user.name.ifBlank { user.id },
            textStyle = textStyle,
            shape = shape,
            contentScale = contentScale,
            contentDescription = contentDescription,
            requestSize = requestSize,
            loadingPlaceholder = loadingPlaceholder,
            previewPlaceholder = previewPlaceholder,
            onClick = onClick,
            initialsAvatarOffset = initialsAvatarOffset
        )
    }
}

@Preview
@Composable
private fun UserAvatarPreview(
    @PreviewParameter(ParticipantsProvider::class) callParticipants: List<CallParticipantState>
) {
    VideoTheme {
        UserAvatar(
            user = callParticipants[0].toUser(),
            modifier = Modifier.size(82.dp)
        )
    }
}
