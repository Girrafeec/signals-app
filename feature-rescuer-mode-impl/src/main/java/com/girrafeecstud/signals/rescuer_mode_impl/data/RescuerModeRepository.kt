/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.data

import com.girrafeecstud.route_builder_api.data.RoutesDataSource
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import com.girrafeecstud.signals.rescuer_mode_impl.domain.IRescuerModeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RescuerModeRepository @Inject constructor(
    private val authDataSource: IAuthDataSource,
    private val rescuerModeDataSource: IRescuerModeDataSource,
    private val routesDataSource: RoutesDataSource
) : IRescuerModeRepository {

    override suspend fun acceptSosSignal(signalId: String): Flow<BusinessResult<EmptyResult>> =
        authDataSource.getUserToken()
            .flatMapLatest { authTokenResult ->
                when (authTokenResult) {
                    is BusinessResult.Success -> {
                        rescuerModeDataSource.acceptSosSignal(
                            signalId = signalId,
                            token = authTokenResult._data!!
                        )
                    }
                    is BusinessResult.Error -> flowOf(BusinessResult.Error(authTokenResult.businessErrorType))
                    is BusinessResult.Exception -> flowOf(BusinessResult.Exception(authTokenResult.exceptionType))
                }
            }

    override suspend fun rejectSosSignal(signalId: String): Flow<BusinessResult<EmptyResult>> =
        authDataSource.getUserToken()
            .flatMapLatest { authTokenResult ->
                when (authTokenResult) {
                    is BusinessResult.Success -> {
                        rescuerModeDataSource.rejectSosSignal(
                            signalId = signalId,
                            token = authTokenResult._data!!
                        )
                    }
                    is BusinessResult.Error -> flowOf(BusinessResult.Error(authTokenResult.businessErrorType))
                    is BusinessResult.Exception -> flowOf(BusinessResult.Exception(authTokenResult.exceptionType))
                }
            }

}