package com.girrafeecstud.signals.feature_map.presentation

import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SignalsMapSharedViewModel @Inject constructor(

) : BaseViewModel<SignalsMapSharedUiState>() {

    override var _state: MutableStateFlow<SignalsMapSharedUiState> = MutableStateFlow(SignalsMapSharedUiState.NoExternalDetails)
    override val state: StateFlow<SignalsMapSharedUiState> get() = _state.asStateFlow()


    fun showSignalDetails(signal: EmergencySignal) {
        _state.update { SignalsMapSharedUiState.ShowSignalDetails(signal = signal) }
    }

    fun closeSignalDetails() {
        _state.update { SignalsMapSharedUiState.SignalDetailsClosed }
    }
}