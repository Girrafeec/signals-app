package com.girrafeecstud.signals.navigation.destination

import com.girrafeecstud.signals.navigation.*

sealed class FlowDestination(
    private val _destinationId: Int,
    val defaultScreen: DefaultFlowScreen? = null
) : NavigationDestination {

    override val destinationId: Int
        get() = _destinationId

    class OnBoardFlow(
        _defaultScreen: DefaultOnBoardFlowScreen =
            DefaultOnBoardFlowScreen.SPLASH_SCREEN
    ) : FlowDestination(
        _destinationId = R.id.on_board_flow_fragment,
        defaultScreen = _defaultScreen
    )

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
