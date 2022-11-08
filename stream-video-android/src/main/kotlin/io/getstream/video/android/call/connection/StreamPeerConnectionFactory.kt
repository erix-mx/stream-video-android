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

import android.content.Context
import android.media.MediaCodecList
import android.os.Build
import io.getstream.logging.StreamLog
import io.getstream.video.android.model.IceCandidate
import kotlinx.coroutines.CoroutineScope
import org.webrtc.AudioSource
import org.webrtc.AudioTrack
import org.webrtc.DefaultVideoDecoderFactory
import org.webrtc.EglBase
import org.webrtc.HardwareVideoEncoderFactory
import org.webrtc.Logging
import org.webrtc.MediaConstraints
import org.webrtc.MediaStream
import org.webrtc.PeerConnection
import org.webrtc.PeerConnectionFactory
import org.webrtc.SimulcastVideoEncoderFactory
import org.webrtc.SoftwareVideoEncoderFactory
import org.webrtc.VideoSource
import org.webrtc.VideoTrack
import org.webrtc.audio.JavaAudioDeviceModule
import stream.video.sfu.models.Codec
import stream.video.sfu.models.PeerType

public class StreamPeerConnectionFactory(private val context: Context) {

    private val webRtcLogger = StreamLog.getLogger("Call:WebRTC")
    private val audioLogger = StreamLog.getLogger("Call:AudioTrackCallback")

    public val eglBase: EglBase by lazy {
        EglBase.create()
    }

    private val videoDecoderFactory by lazy {
        DefaultVideoDecoderFactory(
            eglBase.eglBaseContext
        )
    }

    private val videoEncoderFactory by lazy {
        val hardwareEncoder = HardwareVideoEncoderFactory(eglBase.eglBaseContext, true, true)
        SimulcastVideoEncoderFactory(hardwareEncoder, SoftwareVideoEncoderFactory())
    }

    private val factory by lazy {
        PeerConnectionFactory.initialize(
            PeerConnectionFactory.InitializationOptions.builder(context)
                .setInjectableLogger({ message, severity, label ->
                    when (severity) {
                        Logging.Severity.LS_VERBOSE -> {
                            webRtcLogger.v { "[onLogMessage] label: $label, message: $message" }
                        }
                        Logging.Severity.LS_INFO -> {
                            webRtcLogger.i { "[onLogMessage] label: $label, message: $message" }
                        }
                        Logging.Severity.LS_WARNING -> {
                            webRtcLogger.w { "[onLogMessage] label: $label, message: $message" }
                        }
                        Logging.Severity.LS_ERROR -> {
                            webRtcLogger.e { "[onLogMessage] label: $label, message: $message" }
                        }
                        Logging.Severity.LS_NONE -> {
                            webRtcLogger.d { "[onLogMessage] label: $label, message: $message" }
                        }
                        else -> {}
                    }
                }, Logging.Severity.LS_VERBOSE)
                .createInitializationOptions()
        )

        PeerConnectionFactory.builder()
            .setVideoDecoderFactory(videoDecoderFactory)
            .setVideoEncoderFactory(videoEncoderFactory)
            .setAudioDeviceModule(
                JavaAudioDeviceModule
                    .builder(context)
                    .setUseHardwareAcousticEchoCanceler(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    .setUseHardwareNoiseSuppressor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    .setAudioRecordErrorCallback(object :
                            JavaAudioDeviceModule.AudioRecordErrorCallback {
                            override fun onWebRtcAudioRecordInitError(p0: String?) {
                                audioLogger.w { "[onWebRtcAudioRecordInitError] $p0" }
                            }

                            override fun onWebRtcAudioRecordStartError(
                                p0: JavaAudioDeviceModule.AudioRecordStartErrorCode?,
                                p1: String?
                            ) {
                                audioLogger.w { "[onWebRtcAudioRecordInitError] $p1" }
                            }

                            override fun onWebRtcAudioRecordError(p0: String?) {
                                audioLogger.w { "[onWebRtcAudioRecordError] $p0" }
                            }
                        })
                    .setAudioTrackErrorCallback(object :
                            JavaAudioDeviceModule.AudioTrackErrorCallback {
                            override fun onWebRtcAudioTrackInitError(p0: String?) {
                                audioLogger.w { "[onWebRtcAudioTrackInitError] $p0" }
                            }

                            override fun onWebRtcAudioTrackStartError(
                                p0: JavaAudioDeviceModule.AudioTrackStartErrorCode?,
                                p1: String?
                            ) {
                                audioLogger.w { "[onWebRtcAudioTrackStartError] $p0" }
                            }

                            override fun onWebRtcAudioTrackError(p0: String?) {
                                audioLogger.w { "[onWebRtcAudioTrackError] $p0" }
                            }
                        })
                    .setAudioRecordStateCallback(object :
                            JavaAudioDeviceModule.AudioRecordStateCallback {
                            override fun onWebRtcAudioRecordStart() {
                                audioLogger.d { "[onWebRtcAudioRecordStart] no args" }
                            }

                            override fun onWebRtcAudioRecordStop() {
                                audioLogger.d { "[onWebRtcAudioRecordStop] no args" }
                            }
                        })
                    .setAudioTrackStateCallback(object :
                            JavaAudioDeviceModule.AudioTrackStateCallback {
                            override fun onWebRtcAudioTrackStart() {
                                audioLogger.d { "[onWebRtcAudioTrackStart] no args" }
                            }

                            override fun onWebRtcAudioTrackStop() {
                                audioLogger.d { "[onWebRtcAudioTrackStop] no args" }
                            }
                        })
                    .createAudioDeviceModule().also {
                        it.setMicrophoneMute(false)
                        it.setSpeakerMute(false)
                    }
            )
            .createPeerConnectionFactory()
    }

    private val systemCodecs by lazy {
        (0 until MediaCodecList.getCodecCount()).map {
            MediaCodecList.getCodecInfoAt(it)
        }
    }

    public fun getVideoEncoderCodecs(): List<Codec> {
        val factoryCodecs = try {
            videoEncoderFactory.supportedCodecs
        } catch (error: Throwable) {
            emptyArray()
        }
        val codecNames = factoryCodecs.map { it.name }

        val supportedSystemCodecs = systemCodecs.filter {
            it.isEncoder && codecNames.any { name ->
                name.lowercase() in it.name.lowercase()
            }
        }

        return factoryCodecs.map { codec ->
            Codec(
                mime = "video/${codec.name}",
                hw_accelerated = supportedSystemCodecs.filter {
                    codec.name.lowercase() in it.name.lowercase()
                }.any {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        it.isHardwareAccelerated
                    } else {
                        false
                    }
                },
                fmtp_line = codec.params.toString()
            )
        }
    }

    public fun getVideoDecoderCodecs(): List<Codec> {
        val factoryCodecs = try {
            videoDecoderFactory.supportedCodecs
        } catch (error: Throwable) {
            emptyArray()
        }

        val codecNames = factoryCodecs.map { it.name }

        val supportedSystemCodecs = systemCodecs.filter {
            !it.isEncoder && codecNames.any { name ->
                name.lowercase() in it.name.lowercase()
            }
        }

        return factoryCodecs.map { codec ->
            Codec(
                mime = "video/${codec.name}",
                hw_accelerated = supportedSystemCodecs.filter {
                    codec.name.lowercase() in it.name.lowercase()
                }.any {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        it.isHardwareAccelerated
                    } else {
                        false
                    }
                },
                fmtp_line = codec.params.toString()
            )
        }
    }

    public fun getAudioEncoderCoders(): List<Codec> {
        val supportedSystemCodecs = systemCodecs.filter {
            it.isEncoder && it.supportedTypes.any { type -> type.contains("audio") }
        }

        return supportedSystemCodecs.map { codec ->
            Codec(
                mime = codec.supportedTypes.firstOrNull() ?: "audio",
                hw_accelerated = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    codec.isHardwareAccelerated
                } else {
                    false
                }
            )
        }.also {
            audioLogger.i { "[getAudioEncoderCoders] codecs: $it" }
        }
    }

    public fun getAudioDecoderCoders(): List<Codec> {
        val supportedSystemCodecs = systemCodecs.filter {
            !it.isEncoder && it.supportedTypes.any { type -> type.contains("audio") }
        }

        return supportedSystemCodecs.map { codec ->
            Codec(
                mime = codec.supportedTypes.firstOrNull() ?: "audio",
                hw_accelerated = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    codec.isHardwareAccelerated
                } else {
                    false
                }
            )
        }.also {
            audioLogger.i { "[getAudioDecoderCoders] codecs: $it" }
        }
    }

    /**
     * Peer connection.
     */
    public fun makePeerConnection(
        coroutineScope: CoroutineScope,
        configuration: PeerConnection.RTCConfiguration,
        type: PeerType,
        mediaConstraints: MediaConstraints,
        onStreamAdded: ((MediaStream) -> Unit)? = null,
        onStreamRemoved: ((MediaStream) -> Unit)? = null,
        onNegotiationNeeded: ((StreamPeerConnection) -> Unit)? = null,
        onIceCandidateRequest: ((IceCandidate, PeerType) -> Unit)? = null
    ): StreamPeerConnection {
        val peerConnection = StreamPeerConnection(
            coroutineScope,
            type,
            mediaConstraints,
            onStreamAdded,
            onStreamRemoved,
            onNegotiationNeeded,
            onIceCandidateRequest
        )
        val connection = makePeerConnectionInternal(
            configuration,
            peerConnection
        )
        return peerConnection.apply { initialize(connection) }
    }

    private fun makePeerConnectionInternal(
        configuration: PeerConnection.RTCConfiguration,
        observer: PeerConnection.Observer?
    ): PeerConnection {
        return requireNotNull(
            factory.createPeerConnection(
                configuration,
                observer,
            )
        )
    }

    /**
     * Audio and Video sources.
     */
    public fun makeVideoSource(isScreencast: Boolean): VideoSource =
        factory.createVideoSource(isScreencast)

    public fun makeVideoTrack(
        source: VideoSource,
        trackId: String
    ): VideoTrack = factory.createVideoTrack(trackId, source)

    public fun makeAudioSource(constraints: MediaConstraints = MediaConstraints()): AudioSource =
        factory.createAudioSource(constraints)

    public fun makeAudioTrack(
        source: AudioSource,
        trackId: String
    ): AudioTrack =
        factory.createAudioTrack(trackId, source)

    internal fun reset() {
    }
}