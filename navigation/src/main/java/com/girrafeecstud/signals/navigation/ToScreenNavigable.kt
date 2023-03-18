package com.girrafeecstud.signals.navigation

interface ToScreenNavigable <in NavigationDestination> {
    fun navigateToScreen(destination: NavigationDestination)
}