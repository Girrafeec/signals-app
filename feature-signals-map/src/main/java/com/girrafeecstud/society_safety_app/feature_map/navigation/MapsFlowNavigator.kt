package com.girrafeecstud.society_safety_app.feature_map.navigation

import android.util.Log
import androidx.navigation.NavController
import com.girrafeecstud.society_safety_app.feature_map.R
import com.girrafeecstud.society_safety_app.navigation.Navigator
import com.girrafeecstud.society_safety_app.navigation.extensions.setStartDestination

class MapsFlowNavigator : Navigator<MapsFlowDestination> {

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: MapsFlowDestination) {
        when (destination) {
            is MapsFlowDestination.MapsFragment -> {
                navController?.navigate(R.id.action_map_fragment)
            }
            is MapsFlowDestination.SosSignalMapsFragment -> {
                navController?.navigate(R.id.action_sos_map_fragment)
            }
        }
    }

    override fun setStartDestination(destination: MapsFlowDestination) {
        navController?.setStartDestination(
            destination = destination, graphId = R.navigation.maps_flow
        )
    }
}