package com.girrafeecstud.signals.feature_map.ui

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.signals.feature_map.BuildConfig
import com.girrafeecstud.signals.feature_map.databinding.FragmentMapBinding
import com.girrafeecstud.signals.feature_map.di.MainComponent
import com.girrafeecstud.signals.feature_map.presentation.MapUiState
import com.girrafeecstud.signals.feature_map.presentation.MapViewModel
import com.girrafeecstud.signals.feature_map.presentation.SignalsMapSharedViewModel
import com.girrafeecstud.signals.feature_map.presentation.SosMapSharedViewModel
import com.girrafeecstud.signals.feature_map.presentation.shared_map.MapSharedUiState
import com.girrafeecstud.signals.feature_map.presentation.shared_map.MapSharedViewModel
import com.girrafeecstud.signals.feature_map.ui.overlay.CurrentLocationOverlay
import com.girrafeecstud.signals.feature_map.ui.overlay.SosSignalOverlay
import com.girrafeecstud.signals.feature_map.ui.overlay.RescuerOverlay
import com.girrafeecstud.signals.feature_map.utils.MapUtils
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
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


class MapFragment : BaseFragment(), SignalsClickEvent, RescuersClickEvent {

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!

    // TODO make something with it!
    private lateinit var currentLocationOverlay: CurrentLocationOverlay

    private var isFirstLocationOverlay = true
    
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mapSharedViewModel: MapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    private val mapViewModel: MapViewModel by viewModels {
        mainViewModelFactory
    }

    private val signalsMapSharedViewModel: SignalsMapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    private val sosMapSharedViewModel: SosMapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        MainComponent.mainComponent.mapComponent().build().inject(this)
        currentLocationOverlay = CurrentLocationOverlay(context.applicationContext)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
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
        Log.i("tag map destr cen", binding.mapView.mapCenter.toString())
        Log.i("tag map destr zoom", binding.mapView.zoomLevelDouble.toString())
        mapViewModel.saveLastMapUtils(
            centerPoint = binding.mapView.getMapCenter(),
            zoom = binding.mapView.zoomLevelDouble
        )
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
                            MapSharedUiState.NoExternalOverlays -> {}
                            MapSharedUiState.ClearRescuers -> {
                                clearRescuers()
                            }
                            is MapSharedUiState.DrawRescuers -> {
                                drawRescuers(rescuers = state.rescuers)
                            }
                            MapSharedUiState.ClearSignals -> {
                                clearSignals()
                            }
                            is MapSharedUiState.DrawSignals -> {
                                drawSignalsLocation(signals = state.signals)
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

    override fun onSignalClick(signal: EmergencySignal?) {

        signal?.let {
            Log.i("tag", "clicked on ${it.signalId}")
            // Animate to signal when clicked on signal overlay
            binding.mapView.controller.animateTo(GeoPoint(signal.signalLatitude, signal.signalLongitude))
            binding.mapView.controller.setZoom(17.5)
            signalsMapSharedViewModel.showSignalDetails(signal = it)
        }
    }

    override fun onRescuerClick(rescuer: Rescuer?) {
        rescuer?.let {
            Log.i("tag", "clicked on ${it.rescuerId}")
            // Animate to signal when clicked on signal overlay
            binding.mapView.controller.animateTo(GeoPoint(rescuer.rescuerLocationLatitude, rescuer.rescuerLocationLongitude))
            binding.mapView.controller.setZoom(17.5)
            sosMapSharedViewModel.showRescuerDetails(rescuer = rescuer)
            //TODO show it on sos map!
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
        binding.mapView.maxZoomLevel = MapUtils.DEFAULT_MAX_ZOOM_LEVEL
        binding.mapView.minZoomLevel = MapUtils.DEFAULT_MIN_ZOOM_LEVEL
        //Set default zoom according to last sessions
        if (mapViewModel.mapZoom != null) {
            Log.i("tag map zoom", mapViewModel.mapZoom.toString())
            binding.mapView.controller.setZoom(mapViewModel.mapZoom!!)
        }
        //Set default center point according to last sessions
        if (mapViewModel.mapCenterPoint != null) {
            Log.i("tag map ce", mapViewModel.mapCenterPoint.toString())
            binding.mapView.controller.setCenter(mapViewModel.mapCenterPoint)
        }
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

    private fun drawRescuers(rescuers: List<Rescuer>?) {
        drawRescuersPaths(rescuers = rescuers)
        drawRescuersLocation(rescuers = rescuers)
    }

    private fun drawRescuersLocation(rescuers: List<Rescuer>?) {
        if (rescuers == null)
            return
        clearRescuersLocation()
        Log.i("tag resc", "draw")
            for (rescuer in rescuers) {
                Log.i("tag resc", rescuer.rescuerFirstName)
                val rescuerOverlay = RescuerOverlay(requireActivity().applicationContext, listener = this)
                rescuerOverlay.setRescuer(rescuer = rescuer)
                binding.mapView.overlays.add(rescuerOverlay)
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
            line.infoWindow = null
            val paint: Paint = line.outlinePaint
            paint.color = ContextCompat.getColor(requireActivity().applicationContext, com.girrafeecstud.core_ui.R.color.orange_700)
            paint.strokeWidth = 20f
            paint.strokeJoin = Paint.Join.ROUND

            // Adding paths down to all overlays
            binding.mapView.overlayManager.add(0, line)
            Log.i("tag", "resc polylines ${binding.mapView.overlays}")
        }
        binding.mapView.invalidate()
    }

    private fun clearRescuers() {
        clearRescuersPaths()
        clearRescuersLocation()
    }

    private fun clearRescuersLocation() {
        for (overlay in binding.mapView.overlays) {
            if (overlay is RescuerOverlay)
                binding.mapView.overlays.remove(overlay)
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

    private fun drawSignalsLocation(signals: List<EmergencySignal>?) {
        if (signals == null)
            return
        clearSignals()
        Log.i("tag sign", "draw")
        for (signal in signals) {
            Log.i("tag sign", signal.signalId)
            val signalOverlay = SosSignalOverlay(requireActivity().applicationContext, this)
            signalOverlay.setSignal(signal = signal)
            binding.mapView.overlays.add(signalOverlay)
        }
        binding.mapView.invalidate()
    }

    private fun clearSignals() {
        for (overlay in binding.mapView.overlays) {
            if (overlay is SosSignalOverlay)
                binding.mapView.overlays.remove(overlay)
        }
        binding.mapView.invalidate()
    }

}