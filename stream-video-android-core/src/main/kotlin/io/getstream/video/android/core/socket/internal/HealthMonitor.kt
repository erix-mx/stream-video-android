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

package io.getstream.video.android.core.socket.internal

import android.os.Handler
import android.os.Looper
import java.util.*
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

private const val HEALTH_CHECK_INTERVAL = 10 * 1000L
private const val MONITOR_INTERVAL = 1000L
private const val NO_EVENT_INTERVAL_THRESHOLD = 30 * 1000L
private const val MONITOR_START_DELAY = 1000L

internal class HealthMonitor(private val healthCallback: HealthCallback) {

    private val delayHandler = Handler(Looper.getMainLooper())
    private var consecutiveFailures = 0
    private var disconnected = false
    private var lastEventDate: Date = Date()

    private val reconnect = Runnable {
        if (needToReconnect()) {
            healthCallback.reconnect()
        }
    }

    private val healthCheck: Runnable = Runnable {
        healthCallback.check()
        delayHandler.postDelayed(monitor, HEALTH_CHECK_INTERVAL)
    }

    private val monitor = Runnable {
        if (needToReconnect()) {
            reconnect()
        } else {
            delayHandler.postDelayed(healthCheck, MONITOR_INTERVAL)
        }
    }

    fun start() {
        lastEventDate = Date()
        disconnected = false
        resetHealthMonitor()
    }

    fun stop() {
        delayHandler.removeCallbacks(monitor)
        delayHandler.removeCallbacks(reconnect)
        delayHandler.removeCallbacks(healthCheck)
    }

    fun ack() {
        lastEventDate = Date()
        delayHandler.removeCallbacks(reconnect)
        disconnected = false
        consecutiveFailures = 0
    }

    fun onDisconnected() {
        disconnected = true
        resetHealthMonitor()
    }

    private fun resetHealthMonitor() {
        stop()
        delayHandler.postDelayed(monitor, MONITOR_START_DELAY)
    }

    private fun reconnect() {
        stop()
        val retryInterval = getRetryInterval(++consecutiveFailures)
        delayHandler.postDelayed(reconnect, retryInterval)
    }

    private fun needToReconnect() =
        disconnected || (Date().time - lastEventDate.time) >= NO_EVENT_INTERVAL_THRESHOLD

    @Suppress("MagicNumber")
    private fun getRetryInterval(consecutiveFailures: Int): Long {
        val max = min(500 + consecutiveFailures * 2000, 25000)
        val min = min(
            max(250, (consecutiveFailures - 1) * 2000),
            25000
        )
        return floor(Math.random() * (max - min) + min).toLong()
    }

    interface HealthCallback {
        fun check()
        fun reconnect()
    }
}