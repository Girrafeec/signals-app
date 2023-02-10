package com.girrafeecstud.society_safety_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.girrafeecstud.society_safety_app.R
import com.girrafeecstud.society_safety_app.app.SocietySafetyApp
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.databinding.ActivityMainBinding
import com.girrafeecstud.society_safety_app.presentation.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

//    @Inject
//    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mainViewModel: MainViewModel by viewModels {
        (applicationContext as SocietySafetyApp).appComponent.mainViewModelFactody()
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

        val navGraph = navController.navInflater.inflate(R.navigation.app_graph)

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

        navGraph.setStartDestination(R.id.mainFlowFragment)
        navController.graph = navGraph

//        navGraph.setStartDestination(R.id.signalsFlowFragment)
//        navController.graph = navGraph

    }

}