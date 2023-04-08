/* Created by Girrafeec */

package com.girrafeecstud.countdown_timer_impl.engine

import com.girrafeecstud.countdown_timer_api.data.BaseCountDownTimerManager
import com.girrafeecstud.countdown_timer_api.engine.BaseCountDownTimerEngine
import com.girrafeecstud.countdown_timer_api.engine.CountDownTimerState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountDownTimerEngine @Inject constructor(
    private val countDownTimerManager: BaseCountDownTimerManager
) : BaseCountDownTimerEngine() {

    override fun startCountDownTimer(durationMillies: Long, tickInterval: Long) {
        countDownTimerManager.startCountDownTimer(
            durationMillis = durationMillies,
            tickInterval = tickInterval
        )
    }

    override fun stopCountDownTimer() {
        countDownTimerManager.stopCountDownTimer()
    }

    override fun getCountDownTimerState(): Flow<CountDownTimerState> =
        countDownTimerManager.state
}