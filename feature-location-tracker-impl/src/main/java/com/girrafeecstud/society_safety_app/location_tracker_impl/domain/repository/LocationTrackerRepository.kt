package com.girrafeecstud.society_safety_app.location_tracker_impl.domain.repository

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface LocationTrackerRepository {

    fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>>

}