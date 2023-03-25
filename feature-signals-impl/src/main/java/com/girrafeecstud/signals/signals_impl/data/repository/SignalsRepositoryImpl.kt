package com.girrafeecstud.signals.signals_impl.data.repository

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_impl.data.datasource.SignalsDataSource
import com.girrafeecstud.signals.signals_impl.domain.SignalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SignalsRepositoryImpl @Inject constructor(
    private val signalsDataSource: SignalsDataSource
) : SignalsRepository {

    override fun getSignalsList(): Flow<BusinessResult<List<EmergencySignal>>> =
        signalsDataSource.getSignalsList(token = "").flowOn(Dispatchers.IO)

    override fun getSignalDetails(signalId: String): Flow<BusinessResult<EmergencySignal>> =
        signalsDataSource.getSignalDetails(token = "", signalId = signalId).flowOn(Dispatchers.IO)
}
