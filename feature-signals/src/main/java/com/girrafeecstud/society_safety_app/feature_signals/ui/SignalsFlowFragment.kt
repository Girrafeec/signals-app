package com.girrafeecstud.society_safety_app.feature_signals.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFlowFragment
import com.girrafeecstud.society_safety_app.feature_signals.R
import com.girrafeecstud.society_safety_app.feature_signals.databinding.FragmentSignalsFlowBinding
import com.girrafeecstud.society_safety_app.feature_signals.di.SignalsFeatureComponent
import com.girrafeecstud.society_safety_app.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.society_safety_app.navigation.ToFlowNavigable
import com.girrafeecstud.society_safety_app.navigation.destination.FlowDestination

class SignalsFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_signals_container
) {

    private var _binding: FragmentSignalsFlowBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        SignalsFeatureComponent.signalsFeatureComponent.inject(this)
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