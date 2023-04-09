package com.girrafeecstud.signals.location_tracker_impl.engine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.util.Log
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.signals.location_tracker_impl.di.LocationTrackerFeatureComponent
import com.girrafeecstud.signals.location_tracker_impl.di.LocationTrackerReceiverComponent
import javax.inject.Inject

class LocationTrackerReceiver : BroadcastReceiver() {

    private var _locationTrackerReceiverComponent: LocationTrackerReceiverComponent? = null

    val locationTrackerReceiverComponent get() = _locationTrackerReceiverComponent!!

    init {
        if (_locationTrackerReceiverComponent == null) {
            _locationTrackerReceiverComponent = LocationTrackerFeatureComponent.locationTrackerFeatureComponent.receiverComponent().build()
        }
    }

    @Inject
    lateinit var locationTrackerEngine: LocationTrackerEngine

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("tag location rec", intent?.action.toString())
        locationTrackerReceiverComponent.injectReceiver(receiver = this)
        if (intent?.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.i("tag location rec", "receiver triggered boot")
            locationTrackerEngine.startLocationTracker(context = context!!) // TODO nul safety problem?
        }
        if (intent?.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
            Log.i("tag location rec", "receiver triggered gps")
            val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            if (isGpsEnabled) {
                Log.d("tag location rec", "GPS is enabled")
                locationTrackerEngine.startLocationTracker(context = context!!) // TODO nul safety problem?
            }
        }

        _locationTrackerReceiverComponent = null
    }
}