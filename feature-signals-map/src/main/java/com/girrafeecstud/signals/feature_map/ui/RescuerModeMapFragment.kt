/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.feature_map.databinding.FragmentRescuerModeMapBinding
import com.girrafeecstud.signals.feature_map.di.MainComponent
import com.girrafeecstud.signals.feature_map.navigation.MapsFlowDestination
import com.girrafeecstud.signals.feature_map.navigation.ToMapScreenNavigable
import com.girrafeecstud.signals.feature_map.presentation.RescuerModeMapUiState
import com.girrafeecstud.signals.feature_map.presentation.RescuerModeMapViewModel
import com.girrafeecstud.signals.feature_map.presentation.shared_map.MapSharedViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class RescuerModeMapFragment : BaseFragment() {

    private var _binding: FragmentRescuerModeMapBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val rescuerModeMapViewModel: RescuerModeMapViewModel by viewModels {
        viewModelFactory
    }

    private val mapSharedViewModel: MapSharedViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainComponent.mainComponent.rescuerModeMapComponent().build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRescuerModeMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setListeners() {
        binding.rejectSignal.setOnClickListener {
            rescuerModeMapViewModel.rejectSignal()
        }
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                rescuerModeMapViewModel.state
                    .onEach { state ->
                        setState(state)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun setState(state: UiState) {
        state as RescuerModeMapUiState

        state.signalRejected.let {
            if (it)
                (requireParentFragment().requireParentFragment() as ToMapScreenNavigable)
                    .navigateToScreen(destination = MapsFlowDestination.SignalsMapFragment())
        }

        state.signalId?.let {
            rescuerModeMapViewModel.getSignalDetails(it)
        }

        state.signals?.let {
            mapSharedViewModel.drawSignals(signals = it)
        }

    }
}