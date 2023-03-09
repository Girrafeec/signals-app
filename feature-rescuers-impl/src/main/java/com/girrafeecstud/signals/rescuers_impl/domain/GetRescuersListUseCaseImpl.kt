package com.girrafeecstud.signals.rescuers_impl.domain

import com.girrafeecstud.signals.rescuers_api.domain.GetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRescuersListUseCaseImpl @Inject constructor(
    private val repository: RescuersRepository
) : GetRescuersListUseCase {

    override fun invoke(): Flow<BusinessResult<List<Rescuer>>> =
        repository.getRescuersList().flowOn(Dispatchers.IO)

}