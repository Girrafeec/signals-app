package com.girrafeecstud.society_safety_app.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.location_tracker_api.utils.TrackerPermissionsUtility
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentSignalsMapBinding
import com.girrafeecstud.society_safety_app.feature_map.di.MainComponent
import com.girrafeecstud.society_safety_app.feature_map.presentation.MapViewModel
import com.girrafeecstud.society_safety_app.feature_map.presentation.SignalsMapViewModel
import com.girrafeecstud.society_safety_app.feature_map.presentation.shared_map.MapSharedViewModel
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
    }

    override fun registerObservers() {
    }

}