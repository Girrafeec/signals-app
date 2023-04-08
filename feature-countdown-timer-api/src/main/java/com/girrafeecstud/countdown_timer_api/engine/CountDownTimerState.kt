package com.girrafeecstud.countdown_timer_api.engine

data class CountDownTimerState(
    val isEnabled: Boolean = false,
    val millisLeft: Long? = null,
    val isPaused: Boolean = false,
    val isFinished: Boolean? = null,
    val isCancelled: Boolean? = false
)
