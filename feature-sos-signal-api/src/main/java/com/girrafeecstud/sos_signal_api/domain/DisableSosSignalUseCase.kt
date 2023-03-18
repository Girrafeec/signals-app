package com.girrafeecstud.sos_signal_api.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface DisableSosSignalUseCase {
    operator fun invoke(): Flow<BusinessResult<Nothing>>
}