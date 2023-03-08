package com.girrafeecstud.society_safety_app.feature_map.presentation

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.society_safety_app.core_base.presentation.base.UiState

sealed class SosSignalMapUIState : UiState {
    object SosSuccessSentMessage : SosSignalMapUIState()
    object SosErrorSentMessage : SosSignalMapUIState()
    object SosSignalDisabled : SosSignalMapUIState()
    class DrawCurrentLocation(location: UserLocation) : SosSignalMapUIState()
    class DrawDefendersLocation() : SosSignalMapUIState()
}
