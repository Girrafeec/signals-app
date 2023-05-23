package com.girrafeecstud.sos_signal_api.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow

interface DisableSosSignalUseCase {
    operator suspend fun invoke(): Flow<BusinessResult<EmptyResult>>
}