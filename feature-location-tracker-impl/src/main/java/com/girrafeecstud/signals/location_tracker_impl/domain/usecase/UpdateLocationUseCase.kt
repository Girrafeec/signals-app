/* Created by Girrafeec */

package com.girrafeecstud.signals.location_tracker_impl.domain.usecase

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.signals.location_tracker_impl.domain.repository.LocationTrackerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateLocationUseCase @Inject constructor(
    private val repository: LocationTrackerRepository
) {

    operator fun invoke(location: UserLocation): Flow<BusinessResult<EmptyResult>> =
        repository.updateLocation(location)

}