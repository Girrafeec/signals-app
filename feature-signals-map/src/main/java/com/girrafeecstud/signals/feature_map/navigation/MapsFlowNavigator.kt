package com.girrafeecstud.signals.feature_map.navigation

import androidx.navigation.NavController
import com.girrafeecstud.signals.feature_map.R
import com.girrafeecstud.signals.navigation.Navigator
import com.girrafeecstud.signals.navigation.extensions.setStartDestination

class MapsFlowNavigator : Navigator<MapsFlowDestination> {

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: MapsFlowDestination) {
        when (destination) {
            is MapsFlowDestination.SignalsMapFragment -> {
                navController?.navigate(R.id.action_map_fragment)
            }
            is MapsFlowDestination.SosMapFragment -> {
                navController?.navigate(R.id.action_sos_map_fragment)
            }
            is MapsFlowDestination.RescuerModeMapFragment -> {
                navController?.navigate(R.id.rescuer_mode_map_fragment)
            }
        }
    }

    override fun setStartDestination(destination: MapsFlowDestination) {
        navController?.setStartDestination(
            destination = destination, graphId = R.navigation.maps_flow
        )
    }
}