package com.girrafeecstud.society_safety_app.feature_map.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.location_tracker_api.utils.TrackerPermissionsUtility
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentMapBinding
import com.girrafeecstud.society_safety_app.feature_map.di.MainComponent
import com.girrafeecstud.society_safety_app.feature_map.presentation.SignalsMapViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import javax.inject.Inject

class MapFragment : BaseFragment() {

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var locationTrackerEngine: LocationTrackerEngine

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val signalsMapViewModel: SignalsMapViewModel by viewModels {
        mainViewModelFactory
    }

    private val currentLocationOverlay = CurrentLocationOverlay()

    private var isFirstLocationOverlay = true

    override fun onAttach(context: Context) {
        MainComponent.mainComponent.injectMap(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMap()

        requestLocationPermissions()
        startLocationTracker()

        registerObservers()
        setListeners()
    }

    override fun onPause() {
        binding.signalsMapView.onPause()
        super.onPause()
    }

    override fun onResume() {
        binding.signalsMapView.onResume()
        super.onResume()
    }

    private fun initMap() {
        //TODO читать больше про настройку карт
        Configuration.getInstance().load(requireActivity().applicationContext, requireActivity().getSharedPreferences("society-safety-app", MODE_PRIVATE))
        binding.signalsMapView.setTileSource(TileSourceFactory.MAPNIK)
        // Disabling built-in zoom controls
        binding.signalsMapView.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
        // Setting zooming map with fingers
        binding.signalsMapView.setMultiTouchControls(true)
        // Set default zoom levels
        binding.signalsMapView.maxZoomLevel = 20.0
        binding.signalsMapView.minZoomLevel = 4.0
        binding.signalsMapView.controller.setZoom(4.0)
//        // Remove vertical repeating and allow horizontal repeating
        binding.signalsMapView.isHorizontalMapRepetitionEnabled = true
        binding.signalsMapView.isVerticalMapRepetitionEnabled = false
        binding.signalsMapView.setScrollableAreaLimitLatitude(
            MapView.getTileSystem().maxLatitude,
            MapView.getTileSystem().minLatitude,
            0
        )
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
        binding.currentLocationButton.setOnClickListener { animateToCurrentLocation() }
        binding.zoomInButton.setOnClickListener { zoomInMap() }
        binding.zoomOutButton.setOnClickListener { zoomOutMap() }
    }

    override fun registerObservers() {
        // TODO do something with result because it may become too complicated to process result here
        lifecycleScope.launchWhenStarted {
            signalsMapViewModel.location
                .onEach { result ->
                    when (result) {
                        is BusinessResult.Success -> {
                            setLocationOnMap(location = result._data)
                        }
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {

                        }
                    }
                }
                .launchIn(lifecycleScope)
        }
    }

    // TODO делать что-то с null safety
    private fun setLocationOnMap(location: UserLocation?) {
        currentLocationOverlay.setLocation(location = location)
        binding.signalsMapView.overlays.add(currentLocationOverlay)
//        binding.signalsMapView.invalidate()
        if (isFirstLocationOverlay) {
            animateToCurrentLocation()
            isFirstLocationOverlay = false
        }
    }

    private fun zoomInMap() {
        binding.signalsMapView.controller.zoomIn()
    }

    private fun zoomOutMap() {
        binding.signalsMapView.controller.zoomOut()
    }

    private fun animateToCurrentLocation() {
        binding.signalsMapView.controller.animateTo(currentLocationOverlay.geoPoint)
        binding.signalsMapView.controller.setZoom(17.5)
    }

}