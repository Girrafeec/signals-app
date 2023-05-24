package com.girrafeecstud.signals.feature_map.presentation

import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal

sealed class SignalsMapUiState {

    object NoEmergencySignals : SignalsMapUiState()

    class DrawSignalsLocations(val signals: List<EmergencySignal>?) : SignalsMapUiState()

    class OpenRescuerMap() : SignalsMapUiState()

}