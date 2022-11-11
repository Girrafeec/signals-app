package com.girrafeecstud.society_safety_app.feature_location_tracker.data.datasource

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_location_tracker.domain.entity.UserLocation
import kotlinx.coroutines.flow.Flow

interface LocationTrackerDataSource {

    suspend fun updateLocation(userId: String, location: UserLocation): Flow<BusinessResult<Nothing>>

}