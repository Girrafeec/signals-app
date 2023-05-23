package com.girrafeecstud.sos_signal_impl.domain.usecase

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_impl.domain.repository.SosSignalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SendSosSignalUseCaseImpl @Inject constructor(
    private val repository: SosSignalRepository
) : SendSosSignalUseCase {

    override suspend fun invoke(sosSignal: SosSignal): Flow<BusinessResult<EmptyResult>> =
        repository.sendSosSignal(sosSignal = sosSignal).flowOn(Dispatchers.IO)
}