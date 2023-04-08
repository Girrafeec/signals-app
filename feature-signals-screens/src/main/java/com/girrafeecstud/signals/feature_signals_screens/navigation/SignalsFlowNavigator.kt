/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.navigation

import androidx.navigation.NavController
import com.girrafeecstud.signals.feature_signals_screens.R
import com.girrafeecstud.signals.feature_signals_screens.SignalsFlowDirections
import com.girrafeecstud.signals.navigation.Navigator
import com.girrafeecstud.signals.navigation.extensions.safeNavigate
import com.girrafeecstud.signals.navigation.extensions.setStartDestination

class SignalsFlowNavigator : Navigator<SignalsFlowDestination> {

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: SignalsFlowDestination) {
        when (destination) {
            is SignalsFlowDestination.SosSignalFragment -> {
                navController?.navigate(R.id.action_global_sos_signal_fragment)
            }
            is SignalsFlowDestination.SosCountDownDialog -> {

                navController?.navigate(R.id.action_global_countdown_dialog)
//                navController?.safeNavigate(
//                    SignalsFlowDirections.actionGlobalSosSignalFragment()
//                )
            }
        }
    }

    override fun setStartDestination(destination: SignalsFlowDestination) {
        navController?.setStartDestination(
            destination = destination, graphId = R.navigation.signals_flow
        )
    }

    fun popBackStack() {
        navController?.popBackStack()
    }
}