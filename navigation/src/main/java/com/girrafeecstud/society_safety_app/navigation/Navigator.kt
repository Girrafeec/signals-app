package com.girrafeecstud.society_safety_app.navigation

interface Navigator<in NavigationDestination> {

    fun navigateToDestination(destination: NavigationDestination)

    fun setStartDestination(destination: NavigationDestination)

}