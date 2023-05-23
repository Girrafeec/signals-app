package com.girrafeecstud.location_tracker_api.data

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

abstract class BaseLocationTrackerDataSource {

    abstract fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>>

    open fun saveLastKnownLocation(location: UserLocation) {}

    open fun saveLastSentLocation(location: UserLocation) {}

    open fun getLastSentLocation(): Flow<BusinessResult<UserLocation>> = emptyFlow()

}