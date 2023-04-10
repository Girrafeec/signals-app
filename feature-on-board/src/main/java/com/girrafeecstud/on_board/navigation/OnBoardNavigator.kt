/* Created by Girrafeec */

package com.girrafeecstud.on_board.navigation

import androidx.navigation.NavController
import com.girrafeecstud.on_board.R
import com.girrafeecstud.signals.navigation.Navigator
import com.girrafeecstud.signals.navigation.extensions.setStartDestination

class OnBoardNavigator : Navigator<OnBoardDestination> {

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: OnBoardDestination) {
        when (destination) {
            is OnBoardDestination.SplashFragment -> {
                navController?.navigate(R.id.action_global_fragment_splash)
            }
            is OnBoardDestination.OnBoardFragment -> {
                navController?.navigate(R.id.action_global_fragment_on_board)
//                navController?.navigate(
//                    OnboardFlowDirections.actionGlobalFragmentOnBoard()
//                )
            }
            is OnBoardDestination.PermissionsFragment -> {
                navController?.navigate(R.id.action_global_fragment_permissions)
//                navController?.navigate(
//                    OnboardFlowDirections.actionGlobalFragmentPermissions()
//                )
            }
        }
    }

    override fun setStartDestination(destination: OnBoardDestination) {
        navController?.setStartDestination(
            destination = destination, graphId = R.navigation.on_board_flow
        )
    }
}