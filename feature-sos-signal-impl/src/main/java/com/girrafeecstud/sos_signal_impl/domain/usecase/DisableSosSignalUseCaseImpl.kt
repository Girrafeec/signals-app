package com.girrafeecstud.sos_signal_impl.domain.usecase

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.DisableSosSignalUseCase
import com.girrafeecstud.sos_signal_impl.domain.repository.SosSignalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DisableSosSignalUseCaseImpl @Inject constructor(
    private val repository: SosSignalRepository
) : DisableSosSignalUseCase {
    override fun invoke(): Flow<BusinessResult<Nothing>> =
        repository.disableSosSignal().flowOn(Dispatchers.IO)
}