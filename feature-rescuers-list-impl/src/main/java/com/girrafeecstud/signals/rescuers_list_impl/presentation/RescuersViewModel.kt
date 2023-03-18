package com.girrafeecstud.signals.rescuers_list_impl.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.signals.rescuers_api.domain.GetRescuersListUseCase
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RescuersViewModel @Inject constructor(
    private val getRescuersListUseCase: GetRescuersListUseCase
) : BaseViewModel<RescuersUiState>() {

    override var _state: MutableStateFlow<RescuersUiState> = MutableStateFlow(RescuersUiState.NoRescuers)
    override val state: StateFlow<RescuersUiState> = _state.asStateFlow()

    init {
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

}