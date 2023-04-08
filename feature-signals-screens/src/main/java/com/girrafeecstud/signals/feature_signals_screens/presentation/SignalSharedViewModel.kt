/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.presentation

import com.girrafeecstud.core_ui.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SignalSharedViewModel @Inject constructor(

) : BaseViewModel<SignalSharedUiState>() {

    override var _state: MutableStateFlow<SignalSharedUiState> = MutableStateFlow(SignalSharedUiState())
    override val state: StateFlow<SignalSharedUiState> = _state.asStateFlow()

    fun cancelSignal() {
        _state.update { it.copy(signalCancelled = true) }
    }

    fun resetState() {
        _state.update { it.copy(signalCancelled = false) }
    }

}