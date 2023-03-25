package com.girrafeecstud.signals.feature_map.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.GetSignalsListUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SignalsMapViewModel @Inject constructor(
    private val getSignalsListUseCase: GetSignalsListUseCase
) : BaseViewModel<SignalsMapUiState>() {

    override var _state: MutableStateFlow<SignalsMapUiState> = MutableStateFlow(SignalsMapUiState.NoEmergencySignals)
    override val state: StateFlow<SignalsMapUiState> get() = _state.asStateFlow()

    init {
        getSignalsListUseCase()
            .onEach { result ->
                when (result) {
                    is BusinessResult.Error -> {}
                    is BusinessResult.Exception -> {}
                    is BusinessResult.Success -> {
                        _state.update {
                            SignalsMapUiState.DrawSignalsLocations(signals = result._data)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

}