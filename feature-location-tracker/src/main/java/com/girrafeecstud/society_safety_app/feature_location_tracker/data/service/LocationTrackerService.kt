package com.girrafeecstud.society_safety_app.feature_location_tracker.data.service

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LifecycleService
import com.girrafeecstud.society_safety_app.feature_location_tracker.utils.TrackingUtility.ACTION_PAUSE_SERVICE
import com.girrafeecstud.society_safety_app.feature_location_tracker.utils.TrackingUtility.ACTION_START_OR_RESUME_SERVICE
import com.girrafeecstud.society_safety_app.feature_location_tracker.utils.TrackingUtility.ACTION_STOP_SERVICE
import com.google.android.gms.location.*
import javax.inject.Inject


class LocationTrackerService : LifecycleService() {

//    @Inject
//    lateinit var societySafetyContext: Context

    private var _locationRequest: LocationRequest? = null

    private var mFusedLocationClient: FusedLocationProviderClient? = null

    private val locationRequest get() = _locationRequest!!

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            //TODO делать через enum
            when(it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    Log.i("tag", "Tracking service started or resumed")
                    startLocationUpdates()
                }
                ACTION_PAUSE_SERVICE -> {
                    Log.i("tag", "Tracking service paused")
                }
                ACTION_STOP_SERVICE -> {
                    Log.i("tag", "Tracking service stopped")
                }
                else -> {
                    Log.i("tag", "Tracking service error")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    //Location Callback
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val currentLocation: Location = locationResult.lastLocation
            Log.d(
                "Locations",
                currentLocation.getLatitude().toString() + "," + currentLocation.getLongitude()
            )
            //ToDO Publish Location
        }
    }


    //TODO косяк большой
    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
            mFusedLocationClient?.requestLocationUpdates(
                locationRequest,
                locationCallback, Looper.myLooper()
            )
    }

    private fun initData() {
        _locationRequest = LocationRequest.create()
        locationRequest.interval = 3000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mFusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this)
    }

}