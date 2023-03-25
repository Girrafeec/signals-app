package com.girrafeecstud.signals.signals_impl.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import kotlinx.coroutines.flow.Flow

interface SignalsRepository {

    fun getSignalsList(): Flow<BusinessResult<List<EmergencySignal>>>

    fun getSignalDetails(signalId: String): Flow<BusinessResult<EmergencySignal>>

}