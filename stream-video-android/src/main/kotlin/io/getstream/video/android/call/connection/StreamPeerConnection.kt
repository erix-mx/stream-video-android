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

package io.getstream.video.android.call.connection

import io.getstream.logging.StreamLog
import io.getstream.video.android.call.utils.addRtcIceCandidate
import io.getstream.video.android.call.utils.createValue
import io.getstream.video.android.call.utils.setValue
import io.getstream.video.android.call.utils.stringify
import io.getstream.video.android.errors.VideoError
import io.getstream.video.android.model.IceCandidate
import io.getstream.video.android.model.toDomainCandidate
import io.getstream.video.android.model.toRtcCandidate
import io.getstream.video.android.utils.Failure
import io.getstream.video.android.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.webrtc.CandidatePairChangeEvent
import org.webrtc.DataChannel
import org.webrtc.IceCandidateErrorEvent
import org.webrtc.MediaConstraints
import org.webrtc.MediaStream
import org.webrtc.MediaStreamTrack
import org.webrtc.PeerConnection
import org.webrtc.RtpParameters
import org.webrtc.RtpReceiver
import org.webrtc.RtpSender
import org.webrtc.RtpTransceiver
import org.webrtc.RtpTransceiver.RtpTransceiverInit
import org.webrtc.SessionDescription
import stream.video.sfu.models.PeerType
import org.webrtc.IceCandidate as RtcIceCandidate

public class StreamPeerConnection(
    private val coroutineScope: CoroutineScope,
    private val type: PeerType,
    private val mediaConstraints: MediaConstraints,
    private val onStreamAdded: ((MediaStream) -> Unit)?,
    private val onStreamRemoved: ((MediaStream) -> Unit)?,
    private val onNegotiationNeeded: ((StreamPeerConnection) -> Unit)?,
    private val onIceCandidate: ((IceCandidate, PeerType) -> Unit)?
) : PeerConnection.Observer {

    private val typeTag = type.stringify()

    private val logger = StreamLog.getLogger("Call:PeerConnection")

    public lateinit var connection: PeerConnection
        private set
    public var videoTransceiver: RtpTransceiver? = null
        private set
    public var audioTransceiver: RtpTransceiver? = null
        private set

    private var statsJob: Job? = null

    private val pendingIceMutex = Mutex()
    private val pendingIceCandidates = mutableListOf<IceCandidate>()

    init {
        logger.i { "<init> #sfu; #$typeTag; mediaConstraints: $mediaConstraints" }
    }

    public fun initialize(peerConnection: PeerConnection) {
        logger.d { "[initialize] #sfu; #$typeTag; peerConnection: $peerConnection" }
        this.connection = peerConnection
    }

    public suspend fun createOffer(): Result<SessionDescription> {
        logger.d { "[createOffer] #sfu; #$typeTag; no args" }
        return createValue {
            connection.createOffer(
                it,
                MediaConstraints()
            )
        }
    }

    public suspend fun createAnswer(): Result<SessionDescription> {
        logger.d { "[createAnswer] #sfu; #$typeTag; no args" }
        return createValue { connection.createAnswer(it, mediaConstraints) }
    }

    public suspend fun setRemoteDescription(sessionDescription: SessionDescription): Result<Unit> {
        logger.d { "[setRemoteDescription] #sfu; #$typeTag; answerSdp: ${sessionDescription.stringify()}" }
        return setValue { connection.setRemoteDescription(it, sessionDescription) }.also {
            pendingIceMutex.withLock {
                pendingIceCandidates.forEach { iceCandidate ->
                    val rtcIceCandidate = iceCandidate.toRtcCandidate()
                    logger.i { "[setRemoteDescription] #sfu; #subscriber; pendingRtcIceCandidate: $rtcIceCandidate" }
                    connection.addRtcIceCandidate(rtcIceCandidate)
                }
                pendingIceCandidates.clear()
            }
        }
    }

    public suspend fun setLocalDescription(sessionDescription: SessionDescription): Result<Unit> {
        logger.d { "[setLocalDescription] #sfu; #$typeTag; offerSdp: ${sessionDescription.stringify()}" }
        return setValue { connection.setLocalDescription(it, sessionDescription) }
    }

    public suspend fun addIceCandidate(iceCandidate: IceCandidate): Result<Unit> {
        if (connection.remoteDescription == null) {
            logger.w { "[addIceCandidate] #sfu; #$typeTag; postponed (no remoteDescription): $iceCandidate" }
            pendingIceMutex.withLock {
                pendingIceCandidates.add(iceCandidate)
            }
            return Failure(VideoError(message = "RemoteDescription is not set"))
        }
        val rtcIceCandidate = iceCandidate.toRtcCandidate()
        logger.d { "[addIceCandidate] #sfu; #$typeTag; rtcIceCandidate: $rtcIceCandidate" }
        return connection.addRtcIceCandidate(rtcIceCandidate).also {
            logger.v { "[addIceCandidate] #sfu; #$typeTag; completed: $it" }
        }
    }

    public fun addTrack(
        mediaStreamTrack: MediaStreamTrack,
        streamIds: List<String>
    ): RtpSender {
        logger.i { "[addTrack] #sfu; #$typeTag; track: ${mediaStreamTrack.stringify()}, streamIds: $streamIds" }
        return connection.addTrack(mediaStreamTrack, streamIds)
    }

    public fun addAudioTransceiver(track: MediaStreamTrack, streamIds: List<String>) {
        logger.i { "[addAudioTransceiver] #sfu; #$typeTag; track: ${track.stringify()}, streamIds: $streamIds" }
        val fullQuality = RtpParameters.Encoding(
            "a",
            true,
            1.0
        ).apply {
            maxBitrateBps = 500_000
        }

        val encodings = listOf(fullQuality)

        val transceiverInit = RtpTransceiverInit(
            RtpTransceiver.RtpTransceiverDirection.SEND_ONLY,
            streamIds,
            encodings
        )

        audioTransceiver = connection.addTransceiver(track, transceiverInit)
    }

    public fun addVideoTransceiver(track: MediaStreamTrack, streamIds: List<String>) {
        logger.d { "[addVideoTransceiver] #sfu; #$typeTag; track: ${track.stringify()}, streamIds: $streamIds" }
        val fullQuality = RtpParameters.Encoding(
            "f",
            true,
            1.0
        ).apply {
            maxBitrateBps = 1_200_000
        }

        val halfQuality = RtpParameters.Encoding(
            "h",
            true,
            2.0
        ).apply {
            maxBitrateBps = 500_000
        }

        val quarterQuality = RtpParameters.Encoding(
            "q",
            true,
            4.0
        ).apply {
            maxBitrateBps = 125_000
        }

        val encodings = listOf(fullQuality, halfQuality, quarterQuality)

        val transceiverInit = RtpTransceiverInit(
            RtpTransceiver.RtpTransceiverDirection.SEND_ONLY,
            streamIds,
            encodings
        )

        videoTransceiver = connection.addTransceiver(track, transceiverInit)
    }

    public fun addLocalStream(mediaStream: MediaStream) {
        logger.i { "[addLocalStream] #sfu; #$typeTag; mediaStream: $mediaStream" }
        connection.addStream(mediaStream)
    }

    /**
     * Peer connection listeners.
     */

    override fun onIceCandidate(candidate: RtcIceCandidate?) {
        logger.i { "[onIceCandidate] #sfu; #$typeTag; candidate: $candidate" }
        if (candidate == null) return

        onIceCandidate?.invoke(candidate.toDomainCandidate(), type)
    }

    override fun onAddStream(stream: MediaStream?) {
        logger.i { "[onAddStream] #sfu; #$typeTag; stream: $stream" }
        if (stream != null) {
            onStreamAdded?.invoke(stream)
        }
    }

    override fun onAddTrack(receiver: RtpReceiver?, mediaStreams: Array<out MediaStream>?) {
        logger.i { "[onAddTrack] #sfu; #$typeTag; receiver: $receiver, mediaStreams: $mediaStreams" }
        mediaStreams?.forEach { mediaStream ->
            logger.v { "[onAddTrack] #sfu; #$typeTag; mediaStream: $mediaStream" }
            mediaStream.audioTracks?.forEach { remoteAudioTrack ->
                logger.v { "[onAddTrack] #sfu; #$typeTag; remoteAudioTrack: ${remoteAudioTrack.stringify()}" }
                remoteAudioTrack.setEnabled(true)
            }
            onStreamAdded?.invoke(mediaStream)
        }
    }

    override fun onRenegotiationNeeded() {
        logger.i { "[onRenegotiationNeeded] #sfu; #$typeTag; no args" }
        onNegotiationNeeded?.invoke(this)
    }

    override fun onRemoveStream(stream: MediaStream?) {
        logger.i { "[onRemoveStream] #sfu; #$typeTag; stream: $stream" }
        if (stream != null) {
            onStreamRemoved?.invoke(stream)
        }
    }

    override fun onRemoveTrack(receiver: RtpReceiver?) {
        logger.i { "[onRemoveTrack] #sfu; #$typeTag; receiver: $receiver" }
    }

    override fun onSignalingChange(newState: PeerConnection.SignalingState?) {
        logger.d { "[onSignalingChange] #sfu; #$typeTag; newState: $newState" }
    }

    override fun onIceConnectionChange(newState: PeerConnection.IceConnectionState?) {
        logger.i { "[onIceConnectionChange] #sfu; #$typeTag; newState: $newState" }
        when (newState) {
            PeerConnection.IceConnectionState.CLOSED,
            PeerConnection.IceConnectionState.FAILED,
            PeerConnection.IceConnectionState.DISCONNECTED -> {
                statsJob?.cancel()
            }
            PeerConnection.IceConnectionState.CONNECTED -> {
                statsJob = observeStats()
            }
            else -> Unit
        }
    }

    private fun observeStats() = coroutineScope.launch {
        while (isActive) {
            delay(10_000L)
            connection.getStats {
                logger.v { "[observeStats] #sfu; #$typeTag; stats: $it" }
            }
        }
    }

    override fun onIceConnectionReceivingChange(receiving: Boolean) {
        logger.i { "[onIceConnectionReceivingChange] #sfu; #$typeTag; receiving: $receiving" }
    }

    override fun onIceGatheringChange(newState: PeerConnection.IceGatheringState?) {
        logger.i { "[onIceGatheringChange] #sfu; #$typeTag; newState: $newState" }
    }

    override fun onIceCandidatesRemoved(iceCandidates: Array<out org.webrtc.IceCandidate>?) {
        logger.i { "[onIceCandidatesRemoved] #sfu; #$typeTag; iceCandidates: $iceCandidates" }
    }

    override fun onIceCandidateError(event: IceCandidateErrorEvent?) {
        logger.e { "[onIceCandidateError] #sfu; #$typeTag; event: ${event?.stringify()}" }
    }

    override fun onStandardizedIceConnectionChange(newState: PeerConnection.IceConnectionState?) {
        super.onStandardizedIceConnectionChange(newState)
    }

    override fun onConnectionChange(newState: PeerConnection.PeerConnectionState?) {
        logger.i { "[onConnectionChange] #sfu; #$typeTag; newState: $newState" }
    }

    override fun onSelectedCandidatePairChanged(event: CandidatePairChangeEvent?) {
        logger.i { "[onSelectedCandidatePairChanged] #sfu; #$typeTag; event: $event" }
    }

    override fun onTrack(transceiver: RtpTransceiver?) {
        logger.i { "[onTrack] #sfu; #$typeTag; transceiver: $transceiver" }
    }

    override fun onDataChannel(channel: DataChannel?): Unit = Unit

    override fun toString(): String =
        "StreamPeerConnection(type='$typeTag', constraints=$mediaConstraints)"
}