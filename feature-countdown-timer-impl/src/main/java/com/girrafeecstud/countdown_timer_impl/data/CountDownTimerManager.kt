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
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class CountDownTimerManager @Inject constructor(

) : BaseCountDownTimerManager() {

    override var _state: MutableStateFlow<CountDownTimerState> = MutableStateFlow(CountDownTimerState())
    override val state: StateFlow<CountDownTimerState> = _state.asStateFlow()

    private val countDownTimerScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var countDownTimerJob: Job? = null

    private var executorService: ExecutorService? = null

//    override fun startCountDownTimer(durationMillis: Long, tickInterval: Long) {
//        _state.update { it.copy(isEnabled = true, isFinished = false) }
//        Log.i("tag", "countdown started")
//        executorService = Executors.newSingleThreadExecutor()
//        executorService?.submit {
//            var remainingTime = durationMillis
//            while (remainingTime > 0) {
//                Log.i("tag", "ctd $remainingTime")
//                _state.update { it.copy(millisLeft = remainingTime) }
//                Thread.sleep(tickInterval)
//                remainingTime -= tickInterval
//            }
//            stopCountDownTimer()
//        }
//    }
//
//    override fun pauseCountDownTimer() {
//        // Not implemented
//    }
//
//    override fun resumeCountDownTimer() {
//        // Not implemented
//    }
//
//    override fun stopCountDownTimer() {
//        executorService?.shutdownNow()
//        // Firstly, set millisLeft = null and finished status
//        _state.update { it.copy(isEnabled = true, millisLeft = null, isFinished = true) }
//        // Then, disable countdown timer
//        _state.update {
//            delay(1000)
//            it.copy(isEnabled = false, isFinished = null)
//        }
//    }

    override fun startCountDownTimer(
        durationMillies: Long,
        tickInterval: Long
    ) {
        _state.update { it.copy(isEnabled = true, isFinished = false, isCancelled = false) }
        Log.i("tag", "countdown started")
        countDownTimerJob = countDownTimerScope.launch {
            Log.i("tag", "ctd launch")
            var remainingTime = durationMillies
            while (remainingTime > 0) {
                Log.i("tag", "ctd $remainingTime")
                _state.update { it.copy(isEnabled = true, millisLeft = remainingTime) }
                delay(1000)
                remainingTime -= tickInterval
            }
            stopCountDownTimer()
        }
    }

    override fun pauseCountDownTimer() {
    }

    override fun resumeCountDownTimer() {
    }


    //TODO do it with more elegant way. Maybe to change countdown state logic
    override fun stopCountDownTimer() {
        countDownTimerScope.launch {
            countDownTimerJob?.cancel()
            val millisLeft = _state.value.millisLeft ?: 0
            if (millisLeft > 1000) {
                Log.i("tag sos ctd", "fuck")
                _state.update {
                    it.copy(
                        isEnabled = true,
                        millisLeft = null,
                        isFinished = null,
                        isCancelled = true
                    )
                }
            }
            else
                // Firstly, set millisLeft = null and finished status
                _state.update { it.copy(isEnabled = true, millisLeft = null, isFinished = true, isCancelled = false) }
            // Then, disable countdown timer
            _state.update {
                delay(1000)
                it.copy(isEnabled = false, isFinished = null, isCancelled = false)
            }
            cancel()
        }
    }
}