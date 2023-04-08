/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.presentation

data class SosCountDownUiState(
    val countDownCancelled: Boolean? = false,
    val milliesLeft: Long? = null,
    val sosTransmitting: Boolean = false
)