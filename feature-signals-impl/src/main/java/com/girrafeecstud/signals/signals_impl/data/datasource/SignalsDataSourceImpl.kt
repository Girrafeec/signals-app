package com.girrafeecstud.signals.signals_impl.data.datasource

import android.util.Log
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.SignalSender
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignalType
import com.girrafeecstud.signals.signals_impl.data.SignalsRandomizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SignalsDataSourceImpl @Inject constructor(
    private val randomizer: SignalsRandomizer
) : SignalsDataSource {

    override fun getSignalsList(token: String): Flow<BusinessResult<List<EmergencySignal>>> =
        randomizer.getSignals().map {
            delay(3000)
            if (it.isEmpty())
                BusinessResult.Success(_data = null)
            BusinessResult.Success(_data = it)
        }.flowOn(Dispatchers.IO)

    override fun getSignalDetails(
        token: String,
        signalId: String
    ): Flow<BusinessResult<EmergencySignal>> =
        randomizer.getSignalDetails(signalId = signalId)
            .flowOn(Dispatchers.IO)
}