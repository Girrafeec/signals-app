package com.girrafeecstud.sos_signal_api.domain

import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface SendSosSignalUseCase {

    val result: Flow<BusinessResult<Nothing>>

    operator fun invoke(sosSignal: SosSignal): Flow<BusinessResult<Nothing>>
}