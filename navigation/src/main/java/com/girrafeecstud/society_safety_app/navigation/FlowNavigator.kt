package com.girrafeecstud.society_safety_app.navigation

import androidx.navigation.NavController
import com.girrafeecstud.society_safety_app.navigation.destination.FlowDestination
import com.girrafeecstud.society_safety_app.navigation.extensions.setStartDestination

class FlowNavigator : Navigator<FlowDestination> {

    // TODO Inject navController to FlowNavigator
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: FlowDestination) {
        when (destination) {
            is FlowDestination.AuthFlow -> {
                navController?.navigate(
                    MainNavFlowDirections.actionGlobalAuthFlow()
                )
            }
            is FlowDestination.MapsFlow -> {
                navController?.navigate(
                    MainNavFlowDirections
                        .actionGlobalMapsFlow()
                        .setDefaultScreen(destination.defaultScreen as DefaultMapsFlowScreen)
                )
            }
            is FlowDestination.SignalsFlow -> {
                navController?.navigate(
                    MainNavFlowDirections
                        .actionGlobalSignalsFlow()
                        .setDefaultScreen(destination.defaultScreen as DefaultSignalsFlowScreen)
                )
            }
        }
    }

    override fun setStartDestination(destination: FlowDestination) {
        navController?.setStartDestination(
            destination = destination, graphId = R.navigation.main_nav_flow
        )
    }

}