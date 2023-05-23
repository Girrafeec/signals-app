package com.girrafeecstud.sos_signal_api.domain

import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow

interface UpdateSosSignalUseCase {
    operator suspend fun invoke(sosSignal: SosSignal): Flow<BusinessResult<EmptyResult>>
}