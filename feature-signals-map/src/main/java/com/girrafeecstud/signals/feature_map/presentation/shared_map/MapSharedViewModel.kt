package com.girrafeecstud.signals.feature_map.presentation.shared_map

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MapSharedViewModel @Inject constructor(

) : BaseViewModel<MapSharedUiState>() {

    override var _state: MutableStateFlow<MapSharedUiState> = MutableStateFlow(MapSharedUiState.NoExternalOverlays)
    override val state: StateFlow<MapSharedUiState> = _state.asStateFlow()

    fun clearRescuersLocation() {
        _state.update { MapSharedUiState.ClearRescuers }
    }

    fun drawRescuersLocation(rescuers: List<Rescuer>?) {
        _state.update { MapSharedUiState.DrawRescuers(rescuers = rescuers) }
    }

    fun clearSignals() {
        _state.update { MapSharedUiState.ClearSignals }
    }

    fun drawSignals(signals: List<EmergencySignal>?) {
        _state.update { MapSharedUiState.DrawSignals(signals = signals) }
    }
}
