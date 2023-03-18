package com.girrafeecstud.signals.location_tracker_impl.engine

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.signals.location_tracker_impl.service.LocationTrackerService

class LocationTrackerEngineImpl : LocationTrackerEngine {

    override fun startLocationTracker(context: Context) {
        if (isLocationTrackerServiceRunning(context = context))
            return
        val locationTrackerServiceIntent = Intent(context, LocationTrackerService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(locationTrackerServiceIntent)
            return
        }
        context.startService(locationTrackerServiceIntent)
    }

    private fun isLocationTrackerServiceRunning(context: Context): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == LocationTrackerService::class.java.name
        }
    }

}