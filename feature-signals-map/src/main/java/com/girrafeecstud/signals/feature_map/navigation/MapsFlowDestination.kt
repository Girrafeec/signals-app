package com.girrafeecstud.signals.feature_map.navigation

import com.girrafeecstud.signals.feature_map.R
import com.girrafeecstud.signals.navigation.destination.NavigationDestination

sealed class MapsFlowDestination(
    private val _destinationId: Int
) : NavigationDestination {

    override val destinationId: Int
        get() = _destinationId

    class SignalsMapFragment(
    ) : MapsFlowDestination(_destinationId = R.id.signals_map_fragment)

    class SosMapFragment(
    ) : MapsFlowDestination(_destinationId = R.id.sos_map_fragment)

    class RescuerModeMapFragment() : MapsFlowDestination(
        _destinationId = R.id.rescuer_mode_map_fragment
    )

}
