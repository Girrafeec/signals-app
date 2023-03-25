package com.girrafeecstud.signals.signals_impl.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_api.domain.GetSignalsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSignalsListUseCaseImpl @Inject constructor(
    private val repository: SignalsRepository
) : GetSignalsListUseCase {

    override fun invoke(): Flow<BusinessResult<List<EmergencySignal>>> =
        repository.getSignalsList().flowOn(Dispatchers.IO)

}