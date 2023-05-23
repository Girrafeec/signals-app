package com.girrafeecstud.signals.signals_impl.data.datasource

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import kotlinx.coroutines.flow.Flow

interface SignalsDataSource {

    suspend fun getSignalsList(token: String): Flow<BusinessResult<List<EmergencySignal>>>

    suspend fun getSignalDetails(token: String, signalId: String): Flow<BusinessResult<EmergencySignal>>

}