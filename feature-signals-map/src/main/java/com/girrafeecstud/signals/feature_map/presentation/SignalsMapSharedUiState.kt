package com.girrafeecstud.signals.feature_map.presentation

import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal

sealed class SignalsMapSharedUiState : UiState {

    object NoExternalDetails : SignalsMapSharedUiState()

    object SignalDetailsClosed : SignalsMapSharedUiState()

    class ShowSignalDetails(val signal: EmergencySignal) : SignalsMapSharedUiState()

}
