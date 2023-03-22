package com.girrafeecstud.signals.feature_signals_screens.presentation

import com.girrafeecstud.core_ui.presentation.UiState

sealed class SosSignalUiState : com.girrafeecstud.core_ui.presentation.UiState {
    object ChooseSignalData : SosSignalUiState()
    object SignalCountDownDialog : SosSignalUiState()
    object SignalSending : SosSignalUiState()
    object SignalSent : SosSignalUiState()
    // TODO messages to error state
    object Error : SosSignalUiState()
}
