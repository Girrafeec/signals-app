package com.girrafeecstud.signals.navigation

import android.util.Log
import androidx.navigation.NavController
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import com.girrafeecstud.signals.navigation.extensions.setStartDestination

class FlowNavigator : Navigator<FlowDestination> {

    // TODO Inject navController to FlowNavigator
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: FlowDestination) {
        when (destination) {
            is FlowDestination.OnBoardFlow -> {
                navController?.navigate(
                    MainNavFlowDirections
                        .actionGlobalOnBoardFlow()
                        .setDefaultScreen(destination.defaultScreen as DefaultOnBoardFlowScreen)
                )
            }
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
        Log.i("navigation", "backstack")
        for (entry in navController?.backQueue!!)
            Log.i("navigation entry", entry.destination.toString())
    }

    override fun setStartDestination(destination: FlowDestination) {
        navController?.setStartDestination(
            destination = destination, graphId = R.navigation.main_nav_flow
        )
        Log.i("navigation", "backstack")
        for (entry in navController?.backQueue!!)
            Log.i("navigation entry", entry.destination.toString())
    }

}