package com.girrafeecstud.sos_signal_impl.data.datasource

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import kotlinx.coroutines.flow.Flow

interface SosSignalDataSource {

    fun sendSosSignal(sosSignal: SosSignal, userToken: String): Flow<BusinessResult<Nothing>>

    fun updateSosSignal(sosSignal: SosSignal, userToken: String): Flow<BusinessResult<Nothing>>

    fun disableSosSignal(userToken: String): Flow<BusinessResult<Nothing>>

}