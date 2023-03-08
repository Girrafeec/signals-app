package com.girrafeecstud.society_safety_app.feature_signals.presentation

import com.girrafeecstud.society_safety_app.core_base.presentation.base.UiState

sealed class SosSignalUiState : UiState {
    object ChooseSignalData : SosSignalUiState()
    object SignalCountDownDialog : SosSignalUiState()
    object SignalSending : SosSignalUiState()
    object SignalSent : SosSignalUiState()
    // TODO messages to error state
    object Error : SosSignalUiState()
}
