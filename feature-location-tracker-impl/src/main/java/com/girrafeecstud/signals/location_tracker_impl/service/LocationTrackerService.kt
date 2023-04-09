package com.girrafeecstud.signals.location_tracker_impl.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.location_tracker_impl.di.LocationTrackerFeatureComponent
import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class LocationTrackerService : Service() {

    // TODO DI
    private val locationTrackerServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val binder = LocationTrackerBinder()

    @Inject
    internal lateinit var getLastKnownLocationUseCase: GetLastKnownLocationUseCase

    override fun onCreate() {
        LocationTrackerFeatureComponent.locationTrackerFeatureComponent.inject(this)
        super.onCreate()
        //TODO create location tracker component
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = "Foreground Location Tracker"
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_LOW
            )

            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

            notification = Notification.Builder(this, CHANNEL_ID)
                .setContentText("Service is running")
                .setContentTitle("Service enabled")
        }
        else {
            // TODO make something with deprecated method
            notification = Notification.Builder(this)
                .setContentText("Service is running")
                .setContentTitle("Service enabled")
        }

        startLocationTracker()
        startForeground(1001, notification.build())

        return START_STICKY
    }

    override fun onDestroy() {
//        LocationTrackerFeatureComponent.reset()
        super.onDestroy()
        locationTrackerServiceScope.cancel()
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    private fun startLocationTracker() {
        getLastKnownLocationUseCase()
            .onEach { result ->
                when (result) {
                    is BusinessResult.Success -> {
                        Log.i("tag location service", "latitude: ${result._data?.latitude} longitude: ${result._data?.longitude}")
                    }
                    is BusinessResult.Exception-> {
                        Log.i("tag", "exception: ${result.exceptionType.name}")
//                        stopSelf()
                    }
                    is BusinessResult.Error -> {
//                        stopSelf()
                    }
                }
            }
            .launchIn(locationTrackerServiceScope)
    }

    inner class LocationTrackerBinder : Binder() {
        fun getService() = this@LocationTrackerService
    }
}