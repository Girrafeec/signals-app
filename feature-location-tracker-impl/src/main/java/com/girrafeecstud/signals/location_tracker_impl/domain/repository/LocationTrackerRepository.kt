package com.girrafeecstud.signals.location_tracker_impl.domain.repository

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow

interface LocationTrackerRepository {

    fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>>

    fun updateLocation(location: UserLocation): Flow<BusinessResult<EmptyResult>>

}