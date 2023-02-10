package com.girrafeecstud.society_safety_app.core_base.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFlowFragment constructor(
    @IdRes private val navHostFragmentId: Int
): Fragment() {

    protected lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment

        navController = navHostFragment.navController

        setUpNavigation()
    }

    protected open fun setUpNavigation() {}

    protected open fun setListeners() {}
}