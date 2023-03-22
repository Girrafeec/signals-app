package com.girrafeecstud.signals.feature_signals_screens.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.core_ui.ui.BaseFlowFragment
import com.girrafeecstud.signals.feature_signals_screens.R
import com.girrafeecstud.signals.feature_signals_screens.databinding.FragmentSignalsFlowBinding
import com.girrafeecstud.signals.feature_signals_screens.di.SignalsScreensFeatureComponent
import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.navigation.destination.FlowDestination

class SignalsFlowFragment : com.girrafeecstud.core_ui.ui.BaseFlowFragment(
    R.id.nav_host_fragment_signals_container
) {

    private var _binding: FragmentSignalsFlowBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        SignalsScreensFeatureComponent.signalsFeatureComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignalsFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUpNavigation() {}

    fun openSosMapScreen() {
        (requireActivity() as ToFlowNavigable)
            .navigateToScreen(
                destination = FlowDestination.MapsFlow(
                    _defaultScreen = DefaultMapsFlowScreen.SOS_SIGNAL_MAP_SCREEN
                )
            )
    }

}