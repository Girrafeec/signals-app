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
import com.girrafeecstud.signals.R
import com.girrafeecstud.signals.databinding.ActivityMainBinding
import com.girrafeecstud.signals.di.AppComponent
import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.DefaultOnBoardFlowScreen
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.navigation.FlowNavigator
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity(), ToFlowNavigable {

    // TODO DI
    private val flowNavigator = FlowNavigator()

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Signals)
        binding  =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            Log.i("tag", "task ${task.isSuccessful}")
            if (!task.isSuccessful)
                return@addOnCompleteListener
            val token = task.result
            Log.i("tag", "notification token $token")
            // Save token
            lifecycleScope.launch {
                AppComponent.appComponent.notificationTokensDataSource().setNotificationToken(token)
            }
        }
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
                        else {
                            flowNavigator.setStartDestination(
                                destination = FlowDestination.MapsFlow(
                                    _defaultScreen = DefaultMapsFlowScreen.SIGNALS_MAP_SCREEN
                                )
                            )
                        }
                        tempSosEngineScope.cancel()
                    }
                    .launchIn(tempSosEngineScope)
            }
        }

        // If user is unauthorized - open auth flow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                val tempScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
                tempScope.launch {
                    val isUserAuthenticated = AppComponent.appComponent.authDataSource().getUserAuthorizedStatus()
                    if (!isUserAuthenticated) {
                        Log.i("tag start", "not authenticated")
                        flowNavigator.setStartDestination(
                            destination = FlowDestination.AuthFlow()
                        )
                    }
                }
            }
        }

        // TODO fix it!
        // If user already onboarded we choose maps flow as start destination, else - onboard flow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {

                val tempScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
                tempScope.launch {
                    val isUserOnBoarded = AppComponent.appComponent.onBoardDataSource().getOnBoardStatus()
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