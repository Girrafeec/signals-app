package com.girrafeecstud.signals.signals_api.engine

import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal

data class SignalsEngineState(
    val signals: List<EmergencySignal>? = null
)
