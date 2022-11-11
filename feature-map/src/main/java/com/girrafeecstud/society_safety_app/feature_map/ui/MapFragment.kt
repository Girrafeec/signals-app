package com.girrafeecstud.society_safety_app.feature_map.ui

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.service.LocationTrackerService
import com.girrafeecstud.society_safety_app.feature_location_tracker.utils.TrackingUtility
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentMapBinding
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class MapFragment : BaseFragment(), EasyPermissions.PermissionCallbacks {

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sendCommandToService(TrackingUtility.ACTION_STOP_SERVICE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        sendCommandToService(TrackingUtility.ACTION_START_OR_RESUME_SERVICE)
    }

    override fun onPause() {
        super.onPause()
        sendCommandToService(TrackingUtility.ACTION_PAUSE_SERVICE)
    }

    override fun onResume() {
        super.onResume()
        sendCommandToService(TrackingUtility.ACTION_START_OR_RESUME_SERVICE)
    }

    private fun requestPermissions() {
        if (TrackingUtility.hasLocationsPermissions(requireContext()))
            return
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permission to use this app",
                TrackingUtility.REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            return
        }
        EasyPermissions.requestPermissions(
            this,
            "You need to accept location permission to use this app",
            TrackingUtility.REQUEST_CODE_LOCATION_PERMISSION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
            return
        }
        requestPermissions()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun sendCommandToService(action: String) = Intent(
        requireContext(),
        LocationTrackerService::class.java
    ).also {
        it.action = action
        requireContext().startService(it)
    }

}