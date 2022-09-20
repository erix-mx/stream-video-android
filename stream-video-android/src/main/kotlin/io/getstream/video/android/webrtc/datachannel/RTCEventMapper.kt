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

package io.getstream.video.android.webrtc.datachannel

import io.getstream.video.android.events.AudioLevelChangedEvent
import io.getstream.video.android.events.ChangePublishQualityEvent
import io.getstream.video.android.events.ConnectionQualityChangeEvent
import io.getstream.video.android.events.DominantSpeakerChangedEvent
import io.getstream.video.android.events.LocalDeviceChangeEvent
import io.getstream.video.android.events.MuteStateChangeEvent
import io.getstream.video.android.events.PublisherCandidateEvent
import io.getstream.video.android.events.SfuDataEvent
import io.getstream.video.android.events.SfuParticipantJoinedEvent
import io.getstream.video.android.events.SfuParticipantLeftEvent
import io.getstream.video.android.events.SubscriberCandidateEvent
import io.getstream.video.android.events.SubscriberOfferEvent
import io.getstream.video.android.events.VideoQualityChangedEvent
import stream.video.sfu.SfuEvent

public object RTCEventMapper {

    public fun mapEvent(event: SfuEvent): SfuDataEvent {
        return when {
            event.subscriber_offer != null -> SubscriberOfferEvent(event.subscriber_offer.sdp)
            event.connection_quality_changed != null -> with(event.connection_quality_changed) {
                ConnectionQualityChangeEvent(
                    user_id,
                    connection_quality
                )
            }
            event.audio_level_changed != null -> AudioLevelChangedEvent(
                event.audio_level_changed.audio_levels.associate { it.user_id to it.level }
            )
            event.subscriber_candidate != null -> SubscriberCandidateEvent(
                event.subscriber_candidate.candidate
            )
            event.publisher_candidate != null -> PublisherCandidateEvent(event.publisher_candidate.candidate)
            event.change_publish_quality != null -> ChangePublishQualityEvent(event.change_publish_quality)
            event.local_device_change != null -> LocalDeviceChangeEvent(event.local_device_change.type)
            event.mute_state_changed != null -> with(event.mute_state_changed) {
                MuteStateChangeEvent(
                    user_id,
                    audio_muted,
                    video_muted
                )
            }
            event.video_quality_changed != null -> VideoQualityChangedEvent(
                event.video_quality_changed.stream_qualities.associate {
                    it.user_id to it.video_quality
                }
            )
            event.participant_joined != null -> with(event.participant_joined) {
                SfuParticipantJoinedEvent(participant!!, call!!)
            }
            event.participant_left != null -> with(event.participant_left) {
                SfuParticipantLeftEvent(participant!!, call!!)
            }
            event.dominant_speaker_changed != null -> DominantSpeakerChangedEvent(event.dominant_speaker_changed.user_id)
            else -> throw IllegalStateException("Unknown event")
        }
    }
}