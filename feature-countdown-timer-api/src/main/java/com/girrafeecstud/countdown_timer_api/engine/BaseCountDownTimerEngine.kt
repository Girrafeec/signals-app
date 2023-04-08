/* Created by Girrafeec */

package com.girrafeecstud.countdown_timer_api.engine

import com.girrafeecstud.countdown_timer_api.utils.CountDownTimerUtils
import kotlinx.coroutines.flow.Flow

abstract class BaseCountDownTimerEngine {

    abstract fun startCountDownTimer(
        durationMillis: Long = CountDownTimerUtils.DEFAULT_COUNTDOWN_MILLISECONDS,
        tickInterval: Long = CountDownTimerUtils.DEFAULT_COUNTDOWN_TICK_INTERVAL
    )

    protected open fun pauseCountDownTimer() {}

    protected open fun resumeCountDownTimer() {}

    abstract fun stopCountDownTimer()

    abstract fun getCountDownTimerState(): Flow<CountDownTimerState>

}