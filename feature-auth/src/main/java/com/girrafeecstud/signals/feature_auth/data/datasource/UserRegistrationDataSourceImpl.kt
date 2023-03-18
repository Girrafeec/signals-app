package com.girrafeecstud.signals.feature_auth.data.datasource

import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.core_base.domain.base.BusinessErrorType
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.feature_auth.data.network.api.RegistrationApi
import com.girrafeecstud.signals.feature_auth.data.network.mapper.UserRegistrationEntityRequestDtoMapper
import com.girrafeecstud.signals.feature_auth.domain.entity.UserRegistrationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class UserRegistrationDataSourceImpl @Inject constructor(
    private val api: RegistrationApi,
    private val requestMapper: UserRegistrationEntityRequestDtoMapper
): UserRegistrationDataSource {

    override suspend fun registration(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>> {
        val registrationRequest = requestMapper.map(input = user)

        return flow {

            try {

                val response = api.registration(registrationRequest = registrationRequest)
                val responseBody = response.body()

                println(response.code())

                if (response.isSuccessful) {
                    println("here")
                    emit(BusinessResult.Success(_data = null))
                } else {
                    emit(BusinessResult.Error(businessErrorType = BusinessErrorType.USER_ALREADY_EXISTS))
                }
            } catch (exception: NoNetworkConnectionException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
            } catch (exception: SocketTimeoutException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }catch (exception: IOException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }

        }
    }
}
