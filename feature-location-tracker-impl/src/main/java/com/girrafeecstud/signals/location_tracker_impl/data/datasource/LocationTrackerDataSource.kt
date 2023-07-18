package com.girrafeecstud.signals.location_tracker_impl.data.datasource

import android.util.Log
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerClient
import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.GpsIsNotEnabledException
import com.girrafeecstud.signals.core_base.base.LocationPermissionsNotGrantedException
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.location_tracker_impl.extensions.uniqueLocation
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocationTrackerDataSource @Inject constructor(
    private val client: ILocationTrackerClient
) : BaseLocationTrackerDataSource() {

//    override fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>> =
//        client.getLocationUpdates()
//            .map { location ->
//                val userLocation = UserLocation(latitude = location.latitude, longitude = location.longitude)
//                BusinessResult.Success(_data = userLocation)
//            }
//            .flowOn(Dispatchers.IO)

        override fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>> =
            channelFlow {
                val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
                client.getLocationUpdates()
                    .catch { exception ->
                        when (exception) {
                            is LocationPermissionsNotGrantedException -> {
                                send(BusinessResult.Exception(exceptionType = ExceptionType.LOCATION_PERMISSIONS_NOT_GRANTED))
                            }
                            is GpsIsNotEnabledException -> {
                                send(BusinessResult.Exception(exceptionType = ExceptionType.GPS_NOT_ENABLED))
                            }
                        }
                    }
                    .uniqueLocation()
                    .onEach { location ->
                        Log.i("tag", "ds location ${location.latitude} ${location.longitude}")
                        val userLocation = UserLocation(latitude = location.latitude, longitude = location.longitude)
                        send(BusinessResult.Success(_data = userLocation))
                    }
                    .launchIn(scope)
                awaitClose()
            }

}