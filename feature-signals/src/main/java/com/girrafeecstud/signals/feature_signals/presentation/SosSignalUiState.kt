package com.girrafeecstud.signals.feature_signals.presentation

import com.girrafeecstud.signals.core_base.presentation.base.UiState

sealed class SosSignalUiState : UiState {
    object ChooseSignalData : SosSignalUiState()
    object SignalCountDownDialog : SosSignalUiState()
    object SignalSending : SosSignalUiState()
    object SignalSent : SosSignalUiState()
    // TODO messages to error state
    object Error : SosSignalUiState()
}
