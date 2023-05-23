/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuers_impl.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuerDetailsUseCase
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRescuerDetailsUseCase @Inject constructor(
    private val repository: RescuersRepository
) : IGetRescuerDetailsUseCase {

    override suspend fun invoke(rescuerId: String): Flow<BusinessResult<Rescuer>> =
        repository.getRescuerDetails(rescuerId = rescuerId).flowOn(Dispatchers.IO)
}