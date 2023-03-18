package com.girrafeecstud.signals.location_tracker_impl.domain.usecase

import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.location_tracker_impl.domain.repository.LocationTrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLastKnownLocationUseCaseImpl @Inject constructor(
    private val repository: LocationTrackerRepository
): GetLastKnownLocationUseCase {

    override operator fun invoke(): Flow<BusinessResult<UserLocation>> =
        // TODO create app dispatchers and make di with it
        repository.getLastKnownLocation().flowOn(Dispatchers.IO)

}