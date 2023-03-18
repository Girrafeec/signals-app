package com.girrafeecstud.signals.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.girrafeecstud.signals.R
import com.girrafeecstud.signals.app.SignalsApp
import com.girrafeecstud.signals.databinding.ActivityMainBinding
import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.presentation.MainViewModel
import com.girrafeecstud.signals.navigation.FlowNavigator
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity(), ToFlowNavigable {

    // TODO DI
    private val flowNavigator = FlowNavigator()

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

//    @Inject
//    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mainViewModel: MainViewModel by viewModels {
        (applicationContext as SignalsApp).appComponent.mainViewModelFactody()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = navHostFragment.navController

        flowNavigator.setNavController(navController)

        // Choose start destination
        mainViewModel.requestUserAuthorizedStatus()
//        mainViewModel.getUserAuthorizedStatus().observe(this) { isUserAuthorized ->
//            when (isUserAuthorized) {
//                false -> {
//                    navGraph.setStartDestination(R.id.authFlowFragment)
//                    navController.graph = navGraph //TODO исправить и добавить как-то ожидание
//                }
//                true -> {
//                    navGraph.setStartDestination(R.id.mainFlowFragment)
//                    navController.graph = navGraph
//                }
//            }
//        }

        // TODO to it after checking for auth status
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                val temporaryNavigationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
                (applicationContext as SignalsApp).appComponent.sosSignalEngine().getSosSignalState()
                    .onEach { state ->
                        if (!(state is SosSignalState.SosSignalDisabled)) {
                            flowNavigator.setStartDestination(
                                destination = FlowDestination.MapsFlow(
                                    _defaultScreen = DefaultMapsFlowScreen.SOS_SIGNAL_MAP_SCREEN
                                )
                            )
                        }
                        else
                            flowNavigator.setStartDestination(
                                destination = FlowDestination.MapsFlow(
                                    _defaultScreen = DefaultMapsFlowScreen.SIGNALS_MAP_SCREEN
                                )
                            )
                        temporaryNavigationScope.cancel()
                    }
                    .launchIn(temporaryNavigationScope)
            }
        }

    }

    override fun navigateToScreen(destination: FlowDestination) {
        flowNavigator.navigateToDestination(destination = destination)
    }
}