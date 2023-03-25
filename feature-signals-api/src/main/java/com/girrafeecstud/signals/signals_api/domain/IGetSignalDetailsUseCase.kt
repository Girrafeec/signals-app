package com.girrafeecstud.signals.signals_api.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import kotlinx.coroutines.flow.Flow

interface IGetSignalDetailsUseCase {

    operator fun invoke(signalId: String): Flow<BusinessResult<EmergencySignal>>

}