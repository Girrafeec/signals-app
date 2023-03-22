package com.girrafeecstud.signals.feature_map.presentation

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.core_ui.presentation.UiState

sealed class SosMapUIState : UiState {
    object SosSuccessSentMessage : SosMapUIState()
    object SosErrorSentMessage : SosMapUIState()
    object SosDisabled : SosMapUIState()
    class DrawRescuersLocations(val rescuers: List<Rescuer>?) : SosMapUIState()
}
