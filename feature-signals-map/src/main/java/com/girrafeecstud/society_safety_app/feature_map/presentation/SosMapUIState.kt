package com.girrafeecstud.society_safety_app.feature_map.presentation

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.society_safety_app.core_base.presentation.base.UiState

sealed class SosMapUIState : UiState {
    object SosSuccessSentMessage : SosMapUIState()
    object SosErrorSentMessage : SosMapUIState()
    object SosDisabled : SosMapUIState()
    class DrawRescuersLocations(val rescuers: List<Rescuer>?) : SosMapUIState()
}
