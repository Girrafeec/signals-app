/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_details_impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuerDetailsUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RescuerDetailsViewModel @Inject constructor(
    private val getRescuerDetailsUseCase: IGetRescuerDetailsUseCase
) : BaseViewModel<RescuerDetailsUiState>() {

    override var _state: MutableStateFlow<RescuerDetailsUiState> = MutableStateFlow(
        RescuerDetailsUiState()
    )
    override val state: StateFlow<RescuerDetailsUiState> = _state.asStateFlow()

    init {
        Log.i("tag resc det", "vm init")
    }

    fun fetchRescuerDetails(rescuerId: String) {
        getRescuerDetailsUseCase(rescuerId = rescuerId)
            .onStart {
                _state.update { it.copy(isLoading = true) }
            }
            .onEach { result ->
                _state.update { it.copy(isLoading = false) }
                when (result) {
                    is BusinessResult.Error -> {}
                    is BusinessResult.Exception -> {
                        // TODO error!!
                    }
                    is BusinessResult.Success -> {
                        Log.i("tag resc det", "got success")
                        // TODO null safety
                        _state.update { it.copy(rescuerDetails = result._data!!) }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("tag resc det", "vm cleared")
    }

}