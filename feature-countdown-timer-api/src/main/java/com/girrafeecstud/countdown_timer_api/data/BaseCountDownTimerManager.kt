/* Created by Girrafeec */

package com.girrafeecstud.countdown_timer_api.data

import com.girrafeecstud.countdown_timer_api.engine.CountDownTimerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseCountDownTimerManager {

     protected abstract var _state: MutableStateFlow<CountDownTimerState>

     abstract val state: StateFlow<CountDownTimerState>

     abstract fun startCountDownTimer(
          durationMillis: Long,
          tickInterval: Long
     )

     protected open fun pauseCountDownTimer() {}

     protected open fun resumeCountDownTimer() {}

     abstract fun stopCountDownTimer()
    
}