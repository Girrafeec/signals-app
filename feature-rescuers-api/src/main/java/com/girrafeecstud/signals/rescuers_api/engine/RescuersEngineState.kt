package com.girrafeecstud.signals.rescuers_api.engine

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer

data class RescuersEngineState(
    val rescuers: List<Rescuer>? = null
)
