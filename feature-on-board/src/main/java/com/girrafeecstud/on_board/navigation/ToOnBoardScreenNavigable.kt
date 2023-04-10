/* Created by Girrafeec */

package com.girrafeecstud.on_board.navigation

import com.girrafeecstud.signals.navigation.ToScreenNavigable

interface ToOnBoardScreenNavigable : ToScreenNavigable<OnBoardDestination> {
    override fun navigateToScreen(destination: OnBoardDestination)
}