package com.girrafeecstud.signals.feature_map.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.core_base.ui.base.BaseFragment
import com.girrafeecstud.signals.feature_map.databinding.FragmentMapBinding
import com.girrafeecstud.signals.feature_map.di.MainComponent
import com.girrafeecstud.signals.feature_map.presentation.MapUiState
import com.girrafeecstud.signals.feature_map.presentation.MapViewModel
import com.girrafeecstud.signals.feature_map.presentation.shared_map.MapSharedUiState
import com.girrafeecstud.signals.feature_map.presentation.shared_map.MapSharedViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import javax.inject.Inject


class MapFragment : BaseFragment() {

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!

    // TODO make something with it!
    private val currentLocationOverlay = CurrentLocationOverlay()

    private var isFirstLocationOverlay = true
    
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mapSharedViewModel: MapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    private val mapViewModel: MapViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        MainComponent.mainComponent.mapComponent().build().inject(this)
        super.onAttach(context)
    }

    override fun onPause() {
        binding.mapView.onPause()
        super.onPause()
    }

    override fun onResume() {
        binding.mapView.onResume()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMap()
    }

    override fun setListeners() {
        binding.zoomInButton.setOnClickListener{ zoomInMap() }
        binding.zoomOutButton.setOnClickListener{ zoomOutMap() }
        binding.currentLocationButton.setOnClickListener { animateToCurrentLocation() }
    }

    override fun registerObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mapSharedViewModel.state
                    .onEach { state ->
                        when (state) {
                            MapSharedUiState.Default -> {}
                            MapSharedUiState.ClearRescuersLocation -> {
                                clearRescuersLocation()
                                clearRescuersPaths()
                            }
                            is MapSharedUiState.DrawRescuersLocations -> {
                                drawRescuersLocation(rescuers = state.rescuers)
                                drawRescuersPaths(rescuers = state.rescuers)
                            }
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }

            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    mapViewModel.state
                        .onEach { state ->
                            when (state) {
                                MapUiState.Default -> {

                                }
                                is MapUiState.DrawCurrentLocation -> drawCurrentLocation(state.location)
                            }
                        }
                        .launchIn(viewLifecycleOwner.lifecycleScope)
                }
            }
    }

    private fun initMap() {
        //TODO читать больше про настройку карт
        Configuration.getInstance().load(requireActivity().applicationContext, requireActivity().applicationContext.getSharedPreferences("society-safety-app",
            Context.MODE_PRIVATE
        ))
        binding.mapView.setTileSource(TileSourceFactory.MAPNIK)
        // Disabling built-in zoom controls
        binding.mapView.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
        // Setting zooming map with fingers
        binding.mapView.setMultiTouchControls(true)
        // Set default zoom levels
        binding.mapView.maxZoomLevel = 20.0
        binding.mapView.minZoomLevel = 4.0
        binding.mapView.controller.setZoom(4.0)
        // Remove vertical repeating and allow horizontal repeating
        binding.mapView.isHorizontalMapRepetitionEnabled = true
        binding.mapView.isVerticalMapRepetitionEnabled = false
        binding.mapView.setScrollableAreaLimitLatitude(
            MapView.getTileSystem().maxLatitude,
            MapView.getTileSystem().minLatitude,
            0
        )
    }

    private fun zoomInMap() {
        binding.mapView.controller.zoomIn()
    }

    private fun zoomOutMap() {
        binding.mapView.controller.zoomOut()
    }

    private fun animateToCurrentLocation() {
        binding.mapView.controller.animateTo(currentLocationOverlay.geoPoint)
        binding.mapView.controller.setZoom(17.5)
    }

    // TODO делать что-то с null safety
    private fun drawCurrentLocation(location: UserLocation?) {
        currentLocationOverlay.setLocation(location = location)
        binding.mapView.overlays.remove(currentLocationOverlay)
        binding.mapView.overlays.add(currentLocationOverlay)
        binding.mapView.invalidate()
        if (isFirstLocationOverlay) {
            animateToCurrentLocation()
            isFirstLocationOverlay = false
        }
    }

    private fun drawRescuersLocation(rescuers: List<Rescuer>?) {
        if (rescuers == null)
            return
        clearRescuersLocation()
        Log.i("tag resc", "draw")
            for (rescuer in rescuers) {
                Log.i("tag resc", rescuer.rescuerFirstName)
                val rescuerOverlay = RescuerOverlay()
                rescuerOverlay.setRescuer(rescuer = rescuer)
                binding.mapView.overlays.add(rescuerOverlay)
            }
        binding.mapView.invalidate()
    }

    private fun clearRescuersLocation() {
        for (overlay in binding.mapView.overlays) {
            if (overlay is RescuerOverlay)
                binding.mapView.overlays.remove(overlay)
        }
        binding.mapView.invalidate()
    }

    private fun drawRescuersPaths(rescuers: List<Rescuer>?) {
        if (rescuers == null)
            return
        clearRescuersPaths()
        for (rescuer in rescuers) {
            Log.i("tag resc", "draw line")

            Log.i("tag", "resc route ${rescuer.route?.routePoints?.size}")

            Log.i("tag", "resc route ${rescuer.route?.routePoints?.first()}")


            val waypoints = rescuer.route?.routePoints?.map { location ->
                GeoPoint(location.latitude, location.longitude)
            }

            val line = Polyline(binding.mapView)
            line.setPoints(waypoints)
            val paint: Paint = line.outlinePaint
            paint.color = Color.GREEN
            paint.strokeWidth = 5f

            binding.mapView.overlayManager.add(line)
            Log.i("tag", "resc polylines ${binding.mapView.overlays}")
        }
        binding.mapView.invalidate()
    }

    private fun clearRescuersPaths() {
        for (overlay in binding.mapView.overlays) {
            if (overlay is Polyline)
                binding.mapView.overlays.remove(overlay)
        }
        binding.mapView.invalidate()
    }

}