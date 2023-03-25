package com.girrafeecstud.signals.signal_details_impl.presentation

import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal

sealed class SignalDetailsUiState : UiState {

    object SignalDetailsLoading : SignalDetailsUiState()

    object SignalDetailsLoadingError : SignalDetailsUiState()

    object SignalDisabled : SignalDetailsUiState()

    class ShowSignalDetails(val signal: EmergencySignal) : SignalDetailsUiState()

}
