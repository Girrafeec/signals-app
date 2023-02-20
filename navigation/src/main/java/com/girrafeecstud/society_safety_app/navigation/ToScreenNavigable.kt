package com.girrafeecstud.society_safety_app.navigation

interface ToScreenNavigable <in NavigationDestination> {
    fun navigateToScreen(destination: NavigationDestination)
}