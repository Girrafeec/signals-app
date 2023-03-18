package com.girrafeecstud.signals.location_tracker_impl.engine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
        locationTrackerReceiverComponent.injectReceiver(receiver = this)
        if (intent?.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.i("tag", "receiver triggered")
            locationTrackerEngine.startLocationTracker(context = context!!) // TODO nul safety problem?
        }
        _locationTrackerReceiverComponent = null
    }
}