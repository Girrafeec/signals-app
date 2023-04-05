/* Created by Girrafeec */

package com.girrafeecstud.countdown_timer_impl.data

import android.util.Log
import com.girrafeecstud.countdown_timer_api.data.BaseCountDownTimerManager
import com.girrafeecstud.countdown_timer_api.engine.CountDownTimerState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class CountDownTimerManager @Inject constructor(

) : BaseCountDownTimerManager() {

    override var _state: MutableStateFlow<CountDownTimerState> = MutableStateFlow(CountDownTimerState())
    override val state: StateFlow<CountDownTimerState> = _state.asStateFlow()

    private val countDownTimerScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun startCountDownTimer(
        durationMillies: Long,
        tickInterval: Long
    ) {
        _state.update { it.copy(isEnabled = true, isFinished = false) }
        Log.i("tag", "countdown started")
        countDownTimerScope.launch {
            Log.i("tag", "ctd launch")
            var remainingTime = durationMillies
            while (remainingTime > 0) {
                Log.i("tag", "ctd $remainingTime")
                _state.update { it.copy(isEnabled = true, millliesLeft = remainingTime) }
                delay(1000)
                remainingTime -= tickInterval
            }
            _state.update { it.copy(isEnabled = false, millliesLeft = null, isFinished = true) }
            cancel()
        }
    }

    override fun pauseCountDownTimer() {
    }

    override fun resumeCountDownTimer() {
    }

    override fun stopCountDownTimer() {
        countDownTimerScope.cancel()
        _state.update { it.copy(isEnabled = false, millliesLeft = null, isFinished = true) }
    }
}