package com.girrafeecstud.signals.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource
import com.girrafeecstud.signals.R
import com.girrafeecstud.signals.app.SignalsApp
import com.girrafeecstud.signals.databinding.ActivityMainBinding
import com.girrafeecstud.signals.di.AppComponent
import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.DefaultOnBoardFlowScreen
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.presentation.MainViewModel
import com.girrafeecstud.signals.navigation.FlowNavigator
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ToFlowNavigable {

    // TODO DI
    private val flowNavigator = FlowNavigator()

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

//    @Inject
//    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mainViewModel: MainViewModel by viewModels {
        AppComponent.appComponent.mainViewModelFactody()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
//        AppComponent.reset()
        Log.i("tag start", "activity destroy")
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
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.CREATED) {
//                val temporaryNavigationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
//                (applicationContext as SignalsApp).appComponent.sosSignalEngine().getSosSignalState()
//                    .onEach { state ->
//                        if (!(state is SosSignalState.SosSignalDisabled)) {
//                            flowNavigator.setStartDestination(
//                                destination = FlowDestination.MapsFlow(
//                                    _defaultScreen = DefaultMapsFlowScreen.SOS_SIGNAL_MAP_SCREEN
//                                )
//                            )
//                        }
//                        else
//                            flowNavigator.setStartDestination(
//                                destination = FlowDestination.MapsFlow(
//                                    _defaultScreen = DefaultMapsFlowScreen.SIGNALS_MAP_SCREEN
//                                )
//                            )
//                        temporaryNavigationScope.cancel()
//                    }
//                    .launchIn(temporaryNavigationScope)
//            }
//        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                val tempSosEngineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
                AppComponent.appComponent.sosSignalEngine().getSosSignalState()
                    .onEach { state ->
                        Log.i("tag start", "sos disabled")
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
                        tempSosEngineScope.cancel()
                    }
                    .launchIn(tempSosEngineScope)
            }
        }

        // TODO fix it!
        // If user already onboarded we choose maps flow as start destination, else - onboard flow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {

                val tempScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
                tempScope.launch {
                    val isUserOnBoarded = AppComponent.appComponent.OnBoardDataSource().getOnBoardStatus()
                    if (isUserOnBoarded) {
                        Log.i("tag start", "onboarded")
                    }
                    else {
                        Log.i("tag start", " not onboarded")
                        flowNavigator.setStartDestination(
                            destination = FlowDestination.OnBoardFlow(_defaultScreen = DefaultOnBoardFlowScreen.ON_BOARD_SCREEN)
                        )
                    }
                }
            }
        }

    }

    override fun navigateToScreen(destination: FlowDestination) {
        flowNavigator.navigateToDestination(destination = destination)
    }
}