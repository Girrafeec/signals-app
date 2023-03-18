package com.girrafeecstud.location_tracker_api.data

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface LocationTrackerDataSource {

    fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>>

}