package com.girrafeecstud.signals.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.location_tracker_api.utils.TrackerPermissionsUtility
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.signals.feature_map.R
import com.girrafeecstud.signals.feature_map.databinding.FragmentSignalsMapBinding
import com.girrafeecstud.signals.feature_map.di.MainComponent
import com.girrafeecstud.signals.feature_map.presentation.SignalsMapSharedUiState
import com.girrafeecstud.signals.feature_map.presentation.SignalsMapSharedViewModel
import com.girrafeecstud.signals.feature_map.presentation.SignalsMapUiState
import com.girrafeecstud.signals.feature_map.presentation.SignalsMapViewModel
import com.girrafeecstud.signals.feature_map.presentation.shared_map.MapSharedViewModel
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import com.girrafeecstud.signals.signal_details_api.ui.BaseSignalDetailsFragment
import com.girrafeecstud.signals.signal_details_api.utils.SignalDetailsFeatureUtils
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignalsMapFragment : BaseFragment() {

    private var _binding: FragmentSignalsMapBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var locationTrackerEngine: LocationTrackerEngine

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val signalsMapViewModel: SignalsMapViewModel by viewModels {
        mainViewModelFactory
    }

    private val mapSharedViewModel: MapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    private val signalsMapSharedViewModel: SignalsMapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    @Inject
    lateinit var signalDetailsFragment: BaseSignalDetailsFragment

    override fun onAttach(context: Context) {
        MainComponent.mainComponent.signalsMapComponent().build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignalsMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        signalsMapSharedViewModel.closeSignalDetails()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestLocationPermissions()
        startLocationTracker()
    }

    private fun requestLocationPermissions() {
        if (TrackerPermissionsUtility.locationPermissionsGranted(requireContext()))
            return
        TrackerPermissionsUtility.requestLocationPermissions(requireContext())
    }

    private fun startLocationTracker() {
        locationTrackerEngine.startLocationTracker(context = requireActivity().applicationContext)
    }

    override fun setListeners() {
        binding.sosBtn.setOnClickListener { openSosFragment() }
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signalsMapViewModel.state
                    .onEach { state ->
                        when (state) {
                            is SignalsMapUiState.DrawSignalsLocations -> {
                                mapSharedViewModel.drawSignals(signals = state.signals)
                            }
                            SignalsMapUiState.NoEmergencySignals -> {
                                mapSharedViewModel.clearSignals()
                            }
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signalsMapSharedViewModel.state
                    .onEach { state ->
                        when (state) {
                            SignalsMapSharedUiState.NoExternalDetails -> {}
                            is SignalsMapSharedUiState.ShowSignalDetails -> {
                                showSignalDetails(signal = state.signal)
                            }
                            SignalsMapSharedUiState.SignalDetailsClosed -> {}
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    private fun openSosFragment() {
        mapSharedViewModel.clearSignals()
        (requireActivity() as ToFlowNavigable)
            .navigateToScreen(destination = FlowDestination.SignalsFlow())
    }

    private fun openWatchOverMeScreen() {
        mapSharedViewModel.clearSignals()
        (requireActivity() as ToFlowNavigable)
            .navigateToScreen(destination = FlowDestination.SignalsFlow())
    }

    private fun showSignalDetails(signal: EmergencySignal) {
        if (childFragmentManager.findFragmentByTag(SignalDetailsFeatureUtils.FRAGMENT_SIGNAL_DETAILS_TAG) != null)
            return
        Log.i("tag sos det", "show ${signal.signalId} details")
        // TODO change tag
        signalDetailsFragment.arguments = Bundle().apply {
            this.putString("signalId", signal.signalId)
        }
        childFragmentManager.commit {
            Log.i("tag sos det", "commit fragment")
            add(
                R.id.signal_details_fragment_container,
                signalDetailsFragment,
                SignalDetailsFeatureUtils.FRAGMENT_SIGNAL_DETAILS_TAG
            )
        }
        val bottomSheetBehavior = BottomSheetBehavior.from(requireView().findViewById(R.id.signal_details_fragment_container))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Handle the slide offset, if needed
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.i("tag sos det", "bottom state $newState")
                // Hide the bottom sheet when it is expanded
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    childFragmentManager.commit {
                        remove(signalDetailsFragment)
                    }
                    signalsMapSharedViewModel.closeSignalDetails()
                }
            }
        })
    }

}