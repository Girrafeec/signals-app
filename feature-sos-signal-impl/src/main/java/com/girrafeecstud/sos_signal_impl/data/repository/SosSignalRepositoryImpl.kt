package com.girrafeecstud.sos_signal_impl.data.repository

import android.util.Log
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_impl.data.datasource.SosSignalDataSource
import com.girrafeecstud.sos_signal_impl.domain.repository.SosSignalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SosSignalRepositoryImpl @Inject constructor(
    private val dataSource: SosSignalDataSource,
    private val authDataSource: IAuthDataSource
) : SosSignalRepository {
    override suspend fun sendSosSignal(sosSignal: SosSignal): Flow<BusinessResult<EmptyResult>> =
        authDataSource.getUserToken()
            .flatMapLatest { authTokenResult ->
                when (authTokenResult) {
                    is BusinessResult.Success -> {
                        Log.i("tag sos sign", "auth token ${authTokenResult._data}")
                        dataSource.sendSosSignal(sosSignal, authTokenResult._data!!)
                    }
                    is BusinessResult.Error -> flowOf(BusinessResult.Error(authTokenResult.businessErrorType))
                    is BusinessResult.Exception -> flowOf(BusinessResult.Exception(authTokenResult.exceptionType))
                }
            }

    //    override fun sendSosSignal(sosSignal: SosSignal): Flow<BusinessResult<EmptyResult>> {
//        val userToken = "" // TODO put here token getting from auth repository
//        return dataSource.sendSosSignal(sosSignal = sosSignal, userToken = userToken)
//    }

    override fun updateSosSignal(sosSignal: SosSignal): Flow<BusinessResult<EmptyResult>> {
        TODO()
    }

    override suspend fun disableSosSignal(): Flow<BusinessResult<EmptyResult>> =
        authDataSource.getUserToken()
            .flatMapLatest { authTokenResult ->
                when (authTokenResult) {
                    is BusinessResult.Success -> {
                        dataSource.disableSosSignal(authTokenResult._data!!)
                    }
                    is BusinessResult.Error -> flowOf(BusinessResult.Error(authTokenResult.businessErrorType))
                    is BusinessResult.Exception -> flowOf(BusinessResult.Exception(authTokenResult.exceptionType))
                }
            }

}