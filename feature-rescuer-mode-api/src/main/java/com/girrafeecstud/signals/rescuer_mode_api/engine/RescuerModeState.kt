package com.girrafeecstud.signals.rescuer_mode_api.engine

import com.girrafeecstud.route_builder_api.domain.Route

data class RescuerModeState(
    val signalAccepted: Boolean? = null,
    val signalRejected: Boolean? = null,
    val signalId: String? = null,
    val route: Route? = null
)
