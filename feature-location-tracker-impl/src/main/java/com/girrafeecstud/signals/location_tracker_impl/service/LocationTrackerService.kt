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
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.location_tracker_impl.domain.usecase.UpdateLocationUseCase
import com.girrafeecstud.signals.location_tracker_impl.engine.LocationTrackerState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class LocationTrackerService : Service() {

    // TODO DI
    private val locationTrackerServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val secondServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val binder = LocationTrackerBinder()

    @Inject
    internal lateinit var getLastKnownLocationUseCase: GetLastKnownLocationUseCase

    @Inject
    internal lateinit var updateLocationUseCase: UpdateLocationUseCase

    companion object {
        private var _state: MutableStateFlow<LocationTrackerState> = MutableStateFlow(
            LocationTrackerState()
        )
        private val state = _state.asStateFlow()
    }

    override fun onCreate() {
        LocationTrackerFeatureComponent.locationTrackerFeatureComponent.inject(this)
        super.onCreate()
        //TODO create location tracker component
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification.Builder

        registerObservers()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = "Foreground Location Tracker"
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_LOW
            )

            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

            notification = Notification.Builder(this, CHANNEL_ID)
                .setContentText("Отслеживание местоположения")
                .setContentTitle("Signals")
        }
        else {
            @Suppress("DEPRECATION")
            notification = Notification.Builder(this)
                .setContentText("Отслеживание местоположения")
                .setContentTitle("Signals")
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

    private fun registerObservers() {
            state
                .onEach { state ->
                    state.location?.let { location ->
                        Log.i("tag location service", "new state $state")
                        updateLocation(location)
                    }
                }
                .launchIn(secondServiceScope)
    }

    private fun updateLocation(location: UserLocation) {
        updateLocationUseCase(location = location)
            .onEach { result ->
                Log.i("tag location", "update result $result")
            }
            .launchIn(secondServiceScope)
    }

    private fun startLocationTracker() {
        getLastKnownLocationUseCase()
            .onEach { result ->
                when (result) {
                    is BusinessResult.Success -> {
                        Log.i("tag location service", "latitude: ${result._data?.latitude} longitude: ${result._data?.longitude}")
                        _state.update { it.copy(location = result._data) }
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