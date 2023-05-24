package com.girrafeecstud.signals.feature_map.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuer_mode_api.engine.IRescuerModeEngine
import com.girrafeecstud.signals.signals_api.domain.GetSignalsListUseCase
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignalsMapViewModel @Inject constructor(
    private val getSignalsListUseCase: GetSignalsListUseCase,
    private val signalsEngine: SignalsEngine,
    private val rescuerModeEngine: IRescuerModeEngine
) : BaseViewModel<SignalsMapUiState>() {

    override var _state: MutableStateFlow<SignalsMapUiState> = MutableStateFlow(SignalsMapUiState.NoEmergencySignals)
    override val state: StateFlow<SignalsMapUiState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getSignalsListUseCase()
                .onEach { result ->
                    Log.i("tag signals list vm res", result.toString())
                    when (result) {
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {}
                        is BusinessResult.Success -> {
                            Log.i("tag signals list vm res", "success")
                            _state.update {
                                SignalsMapUiState.DrawSignalsLocations(signals = result._data)
                            }
                        }
                    }
                }
                .launchIn(viewModelScope)
        }

        signalsEngine.getState()
            .onEach { state ->
                if (state.signals != null) {
                    _state.update {
                        SignalsMapUiState.DrawSignalsLocations(signals = state.signals)
                    }
                }
            }
            .launchIn(viewModelScope)

        rescuerModeEngine.getRescuerModeState()
            .onEach { state ->
                if (state.signalAccepted == true)
                _state.update {
                    SignalsMapUiState.OpenRescuerMap()
                }
            }
            .launchIn(viewModelScope)
    }

}