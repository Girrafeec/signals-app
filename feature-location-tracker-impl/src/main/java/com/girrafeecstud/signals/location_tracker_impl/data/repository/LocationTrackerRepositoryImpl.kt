package com.girrafeecstud.signals.location_tracker_impl.data.repository

import android.util.Log
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.signals.location_tracker_impl.data.datasource.LocalLocationTrackerDataSource
import com.girrafeecstud.signals.location_tracker_impl.domain.repository.LocationTrackerRepository
import com.girrafeecstud.signals.location_tracker_impl.utils.TrackerUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

class LocationTrackerRepositoryImpl @Inject constructor(
    @Named(TrackerUtility.LOCATION_TRACKER_DATASOURCE)
    private val locationTrackerDataSource: BaseLocationTrackerDataSource,
    @Named(TrackerUtility.LOCAL_LOCATION_TRACKER_DATASOURCE)
    private val localLocationTrackerDataSource: BaseLocationTrackerDataSource
) : LocationTrackerRepository {

    //    override fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>> =
//            localLocationTrackerDataSource.getLastKnownLocation()
//                .flatMapLatest { localLocationResult ->
//                    flowOf(localLocationResult)
//                    Log.i("tag location local", localLocationResult.toString())
//                    locationTrackerDataSource.getLastKnownLocation()
//                        .flatMapLatest { locationResult ->
//                            Log.i("tag location", locationResult.toString())
//                            if (locationResult is BusinessResult.Success) {
//                                Log.i("tag location new", locationResult.toString())
//                                val location = locationResult._data!!
//                                localLocationTrackerDataSource.saveLastKnownLocation(location)
//                            }
//                            flowOf(locationResult)
//                        }
//                }.flowOn(Dispatchers.IO)

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
}