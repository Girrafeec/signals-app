package com.girrafeecstud.society_safety_app.navigation

import com.girrafeecstud.society_safety_app.navigation.destination.FlowDestination

interface ToFlowNavigable : ToScreenNavigable<FlowDestination> {
    override fun navigateToScreen(destination: FlowDestination)
}