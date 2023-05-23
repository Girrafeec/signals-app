package com.girrafeecstud.signals.signals_impl.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.IGetSignalDetailsUseCase
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSignalDetailsUseCase @Inject constructor(
    private val repository: SignalsRepository
) : IGetSignalDetailsUseCase {

    override suspend fun invoke(signalId: String): Flow<BusinessResult<EmergencySignal>> =
        repository.getSignalDetails(signalId = signalId).flowOn(Dispatchers.IO)
}