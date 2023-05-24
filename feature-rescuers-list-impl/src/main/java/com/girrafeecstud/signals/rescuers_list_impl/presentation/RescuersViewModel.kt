package com.girrafeecstud.signals.rescuers_list_impl.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuersListUseCase
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class RescuersViewModel @Inject constructor(
    private val getRescuersListUseCase: IGetRescuersListUseCase,
    private val rescuersEngine: RescuersEngine
) : BaseViewModel<RescuersUiState>() {

    override var _state: MutableStateFlow<RescuersUiState> = MutableStateFlow(RescuersUiState.NoRescuers)
    override val state: StateFlow<RescuersUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getRescuersListUseCase()
                .onEach { result ->
                    when (result) {
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {}
                        is BusinessResult.Success -> {
                            // TODO NULL SAFETY!!
                            if (result._data != null)
                                _state.update { RescuersUiState.ShowRescuersList(rescuers = result._data!!) }
                        }
                    }
                }
                .launchIn(viewModelScope)
        }

        rescuersEngine.getState()
            .onEach { rescuersEngineState ->
                if (rescuersEngineState.rescuers != null)
                    _state.update { RescuersUiState.ShowRescuersList(rescuers = rescuersEngineState.rescuers!!) }
            }
            .launchIn(viewModelScope)
    }

}