/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.navigation

import com.girrafeecstud.signals.feature_signals_screens.R
import com.girrafeecstud.signals.navigation.destination.NavigationDestination

sealed class SignalsFlowDestination(
    private val _destinationId: Int
) : NavigationDestination {

    override val destinationId: Int
        get() = _destinationId

    class SosSignalFragment(
    ) : SignalsFlowDestination(_destinationId = R.id.sos_signal_fragment)

    class SosCountDownDialog(
    ) : SignalsFlowDestination(_destinationId = R.id.sos_сountdown_dialog)
}