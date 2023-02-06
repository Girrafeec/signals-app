package com.girrafeecstud.society_safety_app.location_tracker_impl.data.repository

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.location_tracker_impl.data.datasource.LocationTrackerDataSource
import com.girrafeecstud.society_safety_app.location_tracker_impl.domain.repository.LocationTrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationTrackerRepositoryImpl @Inject constructor(
    private val locationTrackerDataSource: LocationTrackerDataSource,
) : LocationTrackerRepository {

    override fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>> =
            locationTrackerDataSource.getLastKnownLocation()
                .flowOn(Dispatchers.IO)
}