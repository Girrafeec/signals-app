package com.girrafeecstud.sos_signal_api.domain

import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface SendSosSignalUseCase {

    operator fun invoke(sosSignal: SosSignal): Flow<BusinessResult<Nothing>>
}