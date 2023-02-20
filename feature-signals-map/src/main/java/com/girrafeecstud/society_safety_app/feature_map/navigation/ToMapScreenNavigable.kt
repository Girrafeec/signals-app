package com.girrafeecstud.society_safety_app.feature_map.navigation

import com.girrafeecstud.society_safety_app.navigation.ToScreenNavigable

interface ToMapScreenNavigable : ToScreenNavigable<MapsFlowDestination> {
    override fun navigateToScreen(destination: MapsFlowDestination)
}