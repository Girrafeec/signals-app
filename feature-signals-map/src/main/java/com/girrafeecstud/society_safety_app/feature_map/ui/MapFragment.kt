package com.girrafeecstud.society_safety_app.feature_map.ui

import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import org.osmdroid.util.GeoPoint
import java.util.*
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
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initMap() {
        //TODO читать больше про настройку карт
        Configuration.getInstance().load(requireActivity().applicationContext, requireActivity().getSharedPreferences("society-safety-app", MODE_PRIVATE))
        binding.signalsMapView.setTileSource(TileSourceFactory.MAPNIK)
    }

    private fun requestLocationPermissions() {
        if (TrackerPermissionsUtility.locationPermissionsGranted(requireContext()))
            return
        TrackerPermissionsUtility.requestLocationPermissions(requireContext())
    }

    private fun startLocationTracker() {
        locationTrackerEngine.startLocationTracker(context = requireActivity().applicationContext)
    }

    private fun registerObservers() {
////        signalsMapViewModel.getData()
//        signalsMapViewModel.location.observe(viewLifecycleOwner) {
//            setLocationOnMap(location = it)
//        }

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
        val currentLocation = GeoPoint(location?.latitude!!, location?.longitude!!)
        val mapController = binding.signalsMapView.controller
        mapController.setZoom(17.5)
//        mapController.setCenter(currentLocation)
        mapController.animateTo(currentLocation)
    }

}