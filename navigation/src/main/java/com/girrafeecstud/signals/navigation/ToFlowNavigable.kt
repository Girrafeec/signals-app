package com.girrafeecstud.signals.navigation

import com.girrafeecstud.signals.navigation.destination.FlowDestination

interface ToFlowNavigable : ToScreenNavigable<FlowDestination> {
    override fun navigateToScreen(destination: FlowDestination)
}