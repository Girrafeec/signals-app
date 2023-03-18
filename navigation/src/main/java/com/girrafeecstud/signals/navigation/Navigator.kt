package com.girrafeecstud.signals.navigation

interface Navigator<in NavigationDestination> {

    fun navigateToDestination(destination: NavigationDestination)

    fun setStartDestination(destination: NavigationDestination)

}