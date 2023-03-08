package com.girrafeecstud.sos_signal_impl.domain.usecase

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.UpdateSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_impl.domain.repository.SosSignalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateSosSignalUseCaseImpl @Inject constructor(
    private val repository: SosSignalRepository
) : UpdateSosSignalUseCase {
    override fun invoke(sosSignal: SosSignal): Flow<BusinessResult<Nothing>> =
        repository.updateSosSignal(sosSignal = sosSignal).flowOn(Dispatchers.IO)
}