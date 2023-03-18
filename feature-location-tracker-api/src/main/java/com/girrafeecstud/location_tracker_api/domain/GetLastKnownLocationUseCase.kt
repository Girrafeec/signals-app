package com.girrafeecstud.location_tracker_api.domain

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface GetLastKnownLocationUseCase {

    operator fun invoke(): Flow<BusinessResult<UserLocation>>

}