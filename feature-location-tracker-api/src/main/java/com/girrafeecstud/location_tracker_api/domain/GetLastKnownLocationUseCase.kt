package com.girrafeecstud.location_tracker_api.domain

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface GetLastKnownLocationUseCase {

    operator fun invoke(): Flow<BusinessResult<UserLocation>>

}