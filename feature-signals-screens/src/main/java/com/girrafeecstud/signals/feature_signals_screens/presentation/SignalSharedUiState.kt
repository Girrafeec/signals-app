/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.presentation

import com.girrafeecstud.core_ui.presentation.UiState

data class SignalSharedUiState(
    val signalCancelled: Boolean = false
) : UiState