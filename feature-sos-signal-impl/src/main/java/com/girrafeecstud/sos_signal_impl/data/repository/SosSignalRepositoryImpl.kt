package com.girrafeecstud.sos_signal_impl.data.repository

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_impl.data.datasource.SosSignalDataSource
import com.girrafeecstud.sos_signal_impl.domain.repository.SosSignalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SosSignalRepositoryImpl @Inject constructor(
    private val dataSource: SosSignalDataSource
) : SosSignalRepository {

    override fun sendSosSignal(sosSignal: SosSignal): Flow<BusinessResult<Nothing>> {
        val userToken = "" // TODO put here token getting from auth repository
        return dataSource.sendSosSignal(sosSignal = sosSignal, userToken = userToken)
    }

    override fun updateSosSignal(sosSignal: SosSignal): Flow<BusinessResult<Nothing>> {
        val userToken = "" // TODO put here token getting from auth repository
        return dataSource.updateSosSignal(sosSignal = sosSignal, userToken = userToken)
    }

    override fun disableSosSignal(): Flow<BusinessResult<Nothing>> {
        val userToken = "" // TODO put here token getting from auth repository
        return dataSource.disableSosSignal(userToken = userToken)
    }

}