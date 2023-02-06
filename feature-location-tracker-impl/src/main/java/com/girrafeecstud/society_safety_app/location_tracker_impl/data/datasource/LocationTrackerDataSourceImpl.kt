package com.girrafeecstud.society_safety_app.location_tracker_impl.data.datasource

import com.girrafeecstud.location_tracker_api.data.LocationTrackerClient
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.society_safety_app.core_base.base.ExceptionType
import com.girrafeecstud.society_safety_app.core_base.base.GpsIsNotEnabledException
import com.girrafeecstud.society_safety_app.core_base.base.LocationPermissionsNotGrantedException
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocationTrackerDataSourceImpl @Inject constructor(
    private val client: LocationTrackerClient
) : LocationTrackerDataSource {

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
                                send(BusinessResult.Exception(exceptionType = ExceptionType.GPS_NOT_ENABLED))
                            }
                            is GpsIsNotEnabledException -> {
                                send(BusinessResult.Exception(exceptionType = ExceptionType.LOCATION_PERMISSIONS_NOT_GRANTED))
                            }
                        }
                    }
                    .onEach { location ->
                        val userLocation = UserLocation(latitude = location.latitude, longitude = location.longitude)
                        send(BusinessResult.Success(_data = userLocation))
                    }
                    .launchIn(scope)
                awaitClose()
            }
}