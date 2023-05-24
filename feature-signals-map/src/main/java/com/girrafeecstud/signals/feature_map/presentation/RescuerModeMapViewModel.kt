/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.rescuer_mode_api.engine.IRescuerModeEngine
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RescuerModeMapViewModel @Inject constructor(
    private val rescuerModeEngine: IRescuerModeEngine,
    private val signalsEngine: SignalsEngine
) : BaseViewModel<RescuerModeMapUiState>() {

    override var _state: MutableStateFlow<RescuerModeMapUiState> =
        MutableStateFlow(RescuerModeMapUiState())
    override val state: StateFlow<RescuerModeMapUiState> = _state.asStateFlow()

    init {
        signalsEngine.getState()
            .onEach { signalsState ->
                _state.update { it.copy(signals = signalsState.signals) }
            }
            .launchIn(viewModelScope)

        rescuerModeEngine.getRescuerModeState()
            .onEach { rescuerModeState ->
                if (rescuerModeState.signalRejected != null)
                    _state.update { it.copy(signalRejected = rescuerModeState.signalRejected!!) }
                _state.update { it.copy(signalId = rescuerModeState.signalId) }
            }
            .launchIn(viewModelScope)
    }

    fun getSignalDetails(signalId: String) {
        signalsEngine.getSignalDetails(signalId)
    }

    fun rejectSignal() {
        rescuerModeEngine.rejectSosSignal()
    }


}