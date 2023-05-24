package com.girrafeecstud.signals.feature_map.presentation

import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal

data class RescuerModeMapUiState(
    val signalRejected: Boolean = false,
    val signalAccepted: Boolean = true,
    val signals: List<EmergencySignal>? = null,
    val signalId: String? = null
) : UiState
