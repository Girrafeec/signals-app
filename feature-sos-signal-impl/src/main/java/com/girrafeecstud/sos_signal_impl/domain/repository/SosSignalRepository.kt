package com.girrafeecstud.sos_signal_impl.domain.repository

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import kotlinx.coroutines.flow.Flow

interface SosSignalRepository {

    fun sendSosSignal(sosSignal: SosSignal): Flow<BusinessResult<Nothing>>

    fun updateSosSignal(sosSignal: SosSignal): Flow<BusinessResult<Nothing>>

    fun disableSosSignal(): Flow<BusinessResult<Nothing>>

}