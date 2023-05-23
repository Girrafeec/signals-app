package com.girrafeecstud.signals.location_tracker_impl.data.repository

import android.util.Log
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.utils.distanceTo
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.signals.location_tracker_impl.data.datasource.LocalLocationTrackerDataSource
import com.girrafeecstud.signals.location_tracker_impl.data.datasource.RemoteLocationsDataSource
import com.girrafeecstud.signals.location_tracker_impl.domain.repository.LocationTrackerRepository
import com.girrafeecstud.signals.location_tracker_impl.utils.TrackerUtility
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

class LocationTrackerRepositoryImpl @Inject constructor(
    @Named(TrackerUtility.LOCATION_TRACKER_DATASOURCE)
    private val locationTrackerDataSource: BaseLocationTrackerDataSource,
    @Named(TrackerUtility.LOCAL_LOCATION_TRACKER_DATASOURCE)
    private val localLocationTrackerDataSource: BaseLocationTrackerDataSource,
    private val remoteLocationsDataSource: RemoteLocationsDataSource,
    private val authDataSource: IAuthDataSource
) : LocationTrackerRepository {

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    // TODO make it work
    override fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>> =
        localLocationTrackerDataSource.getLastKnownLocation()
            .flatMapLatest { localLocationResult ->
                Log.i("tag location local", localLocationResult.toString())
                flowOf(localLocationResult)
                    .flatMapLatest {
                        locationTrackerDataSource.getLastKnownLocation()
                            .flatMapLatest { locationResult ->
                                Log.i("tag location", locationResult.toString())
                                if (locationResult is BusinessResult.Success) {
                                    Log.i("tag location new", locationResult.toString())
                                    val location = locationResult._data!!
                                    localLocationTrackerDataSource.saveLastKnownLocation(location)
                                }
                                flowOf(locationResult)
                            }
                    }
            }.flowOn(Dispatchers.IO)

    override fun updateLocation(location: UserLocation): Flow<BusinessResult<EmptyResult>> =
        localLocationTrackerDataSource.getLastSentLocation()
            .flatMapLatest { localLocationResult ->
                authDataSource.getUserToken().flatMapLatest { authTokenResult ->
                    when (authTokenResult) {
                        is BusinessResult.Success -> {
                            Log.i("tag location", "rep update")
                            if (localLocationResult is BusinessResult.Success && isNecessaryToUpdate(localLocationResult._data!!, location)) {
                                remoteLocationsDataSource.updateLocation(
                                    authToken = authTokenResult._data!!,
                                    location = location
                                ).flatMapLatest { updateLocationResult ->
                                    if (updateLocationResult is BusinessResult.Success)
                                        localLocationTrackerDataSource.saveLastSentLocation(location)
                                    flowOf(updateLocationResult)
                                }
                            }
                            else {
                                flowOf(BusinessResult.Success(_data = EmptyResult))
                            }
                        }
                        is BusinessResult.Error -> flowOf(BusinessResult.Error(authTokenResult.businessErrorType))
                        is BusinessResult.Exception -> flowOf(BusinessResult.Exception(authTokenResult.exceptionType))
                    }
                }
            }

    // If location changed to 5 meters - update, not - not update
    private fun isNecessaryToUpdate(lastLocation: UserLocation, newLocation: UserLocation): Boolean {
        val diff = lastLocation.distanceTo(newLocation)
        Log.i("tag location", "diff $diff")
        return diff > 10.0
    }


}