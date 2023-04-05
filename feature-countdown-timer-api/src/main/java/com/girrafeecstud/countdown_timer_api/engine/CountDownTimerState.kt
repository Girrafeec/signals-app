package com.girrafeecstud.countdown_timer_api.engine

data class CountDownTimerState(
    val isEnabled: Boolean = false,
    val millliesLeft: Long? = null,
    val isPaused: Boolean = false,
    val isFinished: Boolean = false
)
