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

package io.getstream.video.android.xml.widget.avatar

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.Px
import io.getstream.video.android.xml.R
import io.getstream.video.android.xml.font.TextStyle
import io.getstream.video.android.xml.utils.extensions.dpToPx
import io.getstream.video.android.xml.utils.extensions.getColorCompat
import io.getstream.video.android.xml.utils.extensions.getDimension
import io.getstream.video.android.xml.utils.extensions.getEnum
import io.getstream.video.android.xml.widget.transformer.TransformStyle
import io.getstream.video.android.ui.common.R as RCommon

/**
 * Style for [AvatarView].
 */
public data class AvatarStyle(
    @Px public val avatarBorderWidth: Int,
    @ColorInt public val avatarBorderColor: Int,
    public val avatarInitialsTextStyle: TextStyle,
    public val avatarShape: AvatarShape,
    @Px public val borderRadius: Float,
) {

    internal companion object {
        operator fun invoke(context: Context, attrs: AttributeSet?): AvatarStyle {
            context.obtainStyledAttributes(
                attrs,
                R.styleable.AvatarView,
                0,
                0,
            ).apply {
                val avatarBorderWidth = getDimensionPixelSize(
                    R.styleable.AvatarView_streamAvatarBorderWidth,
                    0
                )

                val avatarBorderColor = getColor(
                    R.styleable.AvatarView_streamAvatarBorderColor,
                    context.getColorCompat(R.color.stream_black)
                )

                val avatarInitialsTextStyle = TextStyle.Builder(this)
                    .size(
                        R.styleable.AvatarView_streamAvatarTextSize,
                        context.getDimension(RCommon.dimen.title3TextSize)
                    )
                    .color(
                        R.styleable.AvatarView_streamAvatarTextColor,
                        context.getColorCompat(RCommon.color.stream_text_avatar_initials)
                    )
                    .font(
                        R.styleable.AvatarView_streamAvatarTextFontAssets,
                        R.styleable.AvatarView_streamAvatarTextFont
                    )
                    .style(
                        R.styleable.AvatarView_streamAvatarTextStyle,
                        Typeface.BOLD
                    )
                    .build()

                val avatarShape =
                    getEnum(R.styleable.AvatarView_streamAvatarShape, AvatarShape.CIRCLE)

                val borderRadius =
                    getDimensionPixelSize(
                        R.styleable.AvatarView_streamAvatarBorderRadius,
                        4.dpToPx()
                    ).toFloat()

                recycle()

                return AvatarStyle(
                    avatarBorderWidth = avatarBorderWidth,
                    avatarBorderColor = avatarBorderColor,
                    avatarInitialsTextStyle = avatarInitialsTextStyle,
                    avatarShape = avatarShape,
                    borderRadius = borderRadius,
                ).let(TransformStyle.avatarStyleTransformer::transform)
            }
        }
    }
}