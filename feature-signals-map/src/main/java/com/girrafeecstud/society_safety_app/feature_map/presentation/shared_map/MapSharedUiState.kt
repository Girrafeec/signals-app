package com.girrafeecstud.society_safety_app.feature_map.presentation.shared_map

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.society_safety_app.core_base.presentation.base.UiState

sealed class MapSharedUiState : UiState {
    // TODO think about another default state
    object Default : MapSharedUiState()
    object ClearRescuersLocation : MapSharedUiState()
    class DrawRescuersLocations(val rescuers: List<Rescuer>?) : MapSharedUiState()
}