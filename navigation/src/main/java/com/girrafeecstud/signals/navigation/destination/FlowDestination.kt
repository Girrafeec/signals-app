package com.girrafeecstud.signals.navigation.destination

import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.DefaultFlowScreen
import com.girrafeecstud.signals.navigation.DefaultSignalsFlowScreen
import com.girrafeecstud.signals.navigation.R

sealed class FlowDestination(
    private val _destinationId: Int,
    val defaultScreen: DefaultFlowScreen? = null
) : NavigationDestination {

    override val destinationId: Int
        get() = _destinationId

    class AuthFlow(
    ) : FlowDestination(_destinationId = R.id.auth_flow_fragment)

    class MapsFlow(
        _defaultScreen: DefaultMapsFlowScreen =
            DefaultMapsFlowScreen.SIGNALS_MAP_SCREEN
    ) : FlowDestination(
        _destinationId = R.id.maps_flow_fragment,
        defaultScreen = _defaultScreen
    )

    class SignalsFlow(
        _defaultScreen: DefaultSignalsFlowScreen =
            DefaultSignalsFlowScreen.SOS_SIGNAL_SCREEN
    ) : FlowDestination(
        _destinationId = R.id.signals_flow_fragment,
        defaultScreen = _defaultScreen
    )

}
