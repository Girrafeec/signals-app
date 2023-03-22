package com.girrafeecstud.signals.feature_map.presentation

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.core_ui.presentation.UiState

sealed class MapUiState : UiState {
    // TODO think about another default state
    object Default : MapUiState()
    class DrawCurrentLocation(val location: UserLocation) : MapUiState()
}