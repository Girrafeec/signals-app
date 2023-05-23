package com.girrafeecstud.sos_signal_impl.data.datasource

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import kotlinx.coroutines.flow.Flow

interface SosSignalDataSource {

    suspend fun sendSosSignal(sosSignal: SosSignal, userToken: String): Flow<BusinessResult<EmptyResult>>

    suspend fun updateSosSignal(sosSignal: SosSignal, userToken: String): Flow<BusinessResult<EmptyResult>>

    suspend fun disableSosSignal(userToken: String): Flow<BusinessResult<EmptyResult>>

}