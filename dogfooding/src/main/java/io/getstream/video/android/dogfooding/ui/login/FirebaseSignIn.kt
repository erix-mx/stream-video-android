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

package io.getstream.video.android.dogfooding.ui.login

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract

@Composable
fun rememberRegisterForActivityResult(
    onSignInSuccess: (email: String) -> Unit,
    onSignInFailed: () -> Unit
) = rememberLauncherForActivityResult(
    FirebaseAuthUIActivityResultContract(),
) { result ->

    if (result.resultCode != ComponentActivity.RESULT_OK) {
        onSignInFailed.invoke()
    }

    val email = result?.idpResponse?.email
    if (email != null) {
        onSignInSuccess(email)
    } else {
        onSignInFailed()
    }
}
