/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.presentation

import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SosMapSharedViewModel @Inject constructor(

) : BaseViewModel<SosMapSharedUiState>() {

    override var _state: MutableStateFlow<SosMapSharedUiState> = MutableStateFlow(
        SosMapSharedUiState()
    )
    override val state: StateFlow<SosMapSharedUiState> = _state.asStateFlow()

    fun showRescuerDetails(rescuer: Rescuer) {
        _state.update { it.copy(rescuerDetails = rescuer) }
    }

    fun closeRescuerDetails() {
        _state.update { it.copy(rescuerDetails = null) }
    }
}