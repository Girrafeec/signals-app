package com.girrafeecstud.signals.feature_map.navigation

import com.girrafeecstud.signals.navigation.ToScreenNavigable

interface ToMapScreenNavigable : ToScreenNavigable<MapsFlowDestination> {
    override fun navigateToScreen(destination: MapsFlowDestination)
}