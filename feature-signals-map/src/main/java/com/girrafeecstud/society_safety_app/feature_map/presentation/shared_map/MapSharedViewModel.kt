package com.girrafeecstud.society_safety_app.feature_map.presentation.shared_map

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MapSharedViewModel @Inject constructor(

) : BaseViewModel<MapSharedUiState>() {

    override var _state: MutableStateFlow<MapSharedUiState> = MutableStateFlow(MapSharedUiState.Default)
    override val state: StateFlow<MapSharedUiState> = _state.asStateFlow()

    fun clearRescuersLocation() {
        _state.update { MapSharedUiState.ClearRescuersLocation }
    }

    fun drawRescuersLocation(rescuers: List<Rescuer>?) {
        _state.update { MapSharedUiState.DrawRescuersLocations(rescuers = rescuers) }
    }
}
