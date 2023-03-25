package com.girrafeecstud.signals.feature_map.presentation.shared_map

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal

sealed class MapSharedUiState : UiState {
    // TODO think about another default state
    object NoExternalOverlays : MapSharedUiState()
    object ClearRescuers : MapSharedUiState()
    class DrawRescuers(val rescuers: List<Rescuer>?) : MapSharedUiState()

    object ClearSignals : MapSharedUiState()

    class DrawSignals(val signals: List<EmergencySignal>?) : MapSharedUiState()
}