package com.girrafeecstud.signals.signals_impl.data.repository

import android.util.Log
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_impl.data.datasource.SignalsDataSource
import com.girrafeecstud.signals.signals_impl.domain.SignalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SignalsRepositoryImpl @Inject constructor(
    private val signalsDataSource: SignalsDataSource,
    private val authDataSource: IAuthDataSource
) : SignalsRepository {

    override suspend fun getSignalsList(): Flow<BusinessResult<List<EmergencySignal>>> =
        authDataSource.getUserToken()
            .flatMapLatest { authTokenResult ->
                when (authTokenResult) {
                    is BusinessResult.Success -> {
                        Log.i("tag sos list", "auth token ${authTokenResult._data}")
                        signalsDataSource.getSignalsList(token = authTokenResult._data!!)
                    }
                    is BusinessResult.Error -> flowOf(BusinessResult.Error(authTokenResult.businessErrorType))
                    is BusinessResult.Exception -> flowOf(BusinessResult.Exception(authTokenResult.exceptionType))
                }
            }

    override suspend fun getSignalDetails(signalId: String): Flow<BusinessResult<EmergencySignal>> =
        authDataSource.getUserToken()
            .flatMapLatest { authTokenResult ->
                when (authTokenResult) {
                    is BusinessResult.Success -> {
                        Log.i("tag sos det", "auth token ${authTokenResult._data}")
                        signalsDataSource.getSignalDetails(token = authTokenResult._data!!, signalId)
                    }
                    is BusinessResult.Error -> flowOf(BusinessResult.Error(authTokenResult.businessErrorType))
                    is BusinessResult.Exception -> flowOf(BusinessResult.Exception(authTokenResult.exceptionType))
                }
            }

}
