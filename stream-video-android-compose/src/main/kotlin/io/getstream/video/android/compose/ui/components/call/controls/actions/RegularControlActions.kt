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

package io.getstream.video.android.compose.ui.components.call.controls.actions

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.core.call.state.CallAction
import io.getstream.video.android.core.call.state.CallDeviceState

/**
 * Represents the set of controls the user can use to change their audio and video device state, or
 * browse other types of settings, leave the call, or implement something custom.
 *
 * @param callDeviceState The state of the media devices for the current user.
 * @param modifier Modifier for styling.
 * @param actions Actions to show to the user with different controls.
 * @param onCallAction Handler when the user triggers an action.
 */
@Composable
public fun RegularControlActions(
    callDeviceState: CallDeviceState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = VideoTheme.colors.barsBackground,
    shape: Shape = VideoTheme.shapes.callControls,
    elevation: Dp = VideoTheme.dimens.callControlsElevation,
    onCallAction: (CallAction) -> Unit = {},
    actions: List<(@Composable () -> Unit)> = buildDefaultCallControlActions(
        callDeviceState,
        onCallAction
    ),
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        elevation = elevation
    ) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(actions) { action ->
                action.invoke()
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RegularCallControlsActionsPreview() {
    VideoTheme {
        RegularControlActions(
            callDeviceState = CallDeviceState(),
            onCallAction = {}
        )
    }
}