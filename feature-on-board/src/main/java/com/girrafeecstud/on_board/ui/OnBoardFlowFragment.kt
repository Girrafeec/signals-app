/* Created by Girrafeec */

package com.girrafeecstud.on_board.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.girrafeecstud.core_ui.ui.BaseFlowFragment
import com.girrafeecstud.on_board.R
import com.girrafeecstud.on_board.databinding.FragmentOnBoardFlowBinding
import com.girrafeecstud.on_board.di.OnBoardFeatureComponent
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import com.girrafeecstud.on_board.navigation.OnBoardDestination
import com.girrafeecstud.on_board.navigation.OnBoardNavigator
import com.girrafeecstud.on_board.navigation.ToOnBoardScreenNavigable
import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.DefaultOnBoardFlowScreen

class OnBoardFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_on_board_container
), ToOnBoardScreenNavigable {

//    private val args: OnBoardFlowFragmentArgs by navArgs()

    private val args: OnBoardFlowFragmentArgs by navArgs()

    private val navigator = OnBoardNavigator()

    private var _binding: FragmentOnBoardFlowBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        OnBoardFeatureComponent.onBoardFeatureComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUpNavigation() {
        Log.i("mapsFlow", "setUpNavigation")
        navigator.setNavController(navController = navController)
        args.defaultScreen.let { screen ->
            when(screen) {
                DefaultOnBoardFlowScreen.SPLASH_SCREEN ->
                    navigator.setStartDestination(
                        destination = OnBoardDestination.SplashFragment
                    )
                DefaultOnBoardFlowScreen.ON_BOARD_SCREEN -> {
                    navigator.setStartDestination(
                        destination = OnBoardDestination.OnBoardFragment
                    )
                }
            }
        }
    }

    override fun navigateToScreen(destination: OnBoardDestination) {
        navigator.navigateToDestination(destination = destination)
    }
}