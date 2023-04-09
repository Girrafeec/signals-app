package com.girrafeecstud.location_tracker_api.data

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

abstract class BaseLocationTrackerDataSource {

    abstract fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>>

    open fun saveLastKnownLocation(location: UserLocation) {}

}