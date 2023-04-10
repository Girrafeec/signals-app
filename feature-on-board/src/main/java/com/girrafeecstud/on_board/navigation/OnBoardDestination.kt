package com.girrafeecstud.on_board.navigation

import com.girrafeecstud.on_board.R
import com.girrafeecstud.signals.navigation.destination.NavigationDestination

sealed class OnBoardDestination(
    private val _destinationId: Int
) : NavigationDestination {
    override val destinationId: Int
        get() = _destinationId

    object SplashFragment : OnBoardDestination(_destinationId = R.id.fragment_splash)

    object OnBoardFragment : OnBoardDestination(_destinationId = R.id.fragment_on_board)

    object PermissionsFragment : OnBoardDestination(_destinationId = R.id.fragment_permissions)

}
