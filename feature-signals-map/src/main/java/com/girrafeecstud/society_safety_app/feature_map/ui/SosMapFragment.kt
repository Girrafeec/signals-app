package com.girrafeecstud.society_safety_app.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_map.R
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentSosMapBinding
import com.girrafeecstud.society_safety_app.feature_map.di.MainComponent
import com.girrafeecstud.society_safety_app.feature_map.navigation.MapsFlowDestination
import com.girrafeecstud.society_safety_app.feature_map.navigation.ToMapScreenNavigable
import com.girrafeecstud.society_safety_app.feature_map.presentation.MapViewModel
import com.girrafeecstud.society_safety_app.feature_map.presentation.SosMapUIState
import com.girrafeecstud.society_safety_app.feature_map.presentation.SosMapViewModel
import com.girrafeecstud.society_safety_app.feature_map.presentation.shared_map.MapSharedViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosMapFragment : BaseFragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val sosMapViewModel: SosMapViewModel by viewModels {
        mainViewModelFactory
    }

    private val mapSharedViewModel: MapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    private var _binding: FragmentSosMapBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        MainComponent.mainComponent.sosMapComponent().build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSosMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setListeners() {
        binding.disableSosBtn.setOnClickListener {
            sosMapViewModel.disableSosSignal(requireActivity().applicationContext)
        }
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sosMapViewModel.state
                    .onEach { state ->
                        when (state) {
                            is SosMapUIState.DrawRescuersLocations ->
                                mapSharedViewModel.drawRescuersLocation(rescuers = state.rescuers)
                            SosMapUIState.SosErrorSentMessage -> showErrorMessage()
                            SosMapUIState.SosSuccessSentMessage -> showSuccessMessage()
                            SosMapUIState.SosDisabled -> {
                                mapSharedViewModel.clearRescuersLocation()
                                (parentFragment?.parentFragment as ToMapScreenNavigable).navigateToScreen(destination = MapsFlowDestination.SignalsMapFragment())
                            }
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    private fun showSuccessMessage() {
        Toast.makeText(requireActivity(), "Sos successfully sent", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorMessage() {
        Toast.makeText(requireActivity(), "Sos error sent", Toast.LENGTH_SHORT).show()
    }
}