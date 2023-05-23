package com.girrafeecstud.sos_signal_impl.domain.repository

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import kotlinx.coroutines.flow.Flow

interface SosSignalRepository {

    suspend fun sendSosSignal(sosSignal: SosSignal): Flow<BusinessResult<EmptyResult>>

    fun updateSosSignal(sosSignal: SosSignal): Flow<BusinessResult<EmptyResult>>

    suspend fun disableSosSignal(): Flow<BusinessResult<EmptyResult>>

}