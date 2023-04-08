/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.navigation

import com.girrafeecstud.signals.navigation.ToScreenNavigable

interface ToSosScreenNavigable : ToScreenNavigable<SignalsFlowDestination> {
    override fun navigateToScreen(destination: SignalsFlowDestination)

    fun popBack()
}