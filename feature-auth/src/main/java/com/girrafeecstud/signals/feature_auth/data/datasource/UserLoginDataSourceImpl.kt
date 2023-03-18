package com.girrafeecstud.signals.feature_auth.data.datasource

import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.core_base.domain.base.BusinessErrorType
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.feature_auth.data.network.api.LoginApi
import com.girrafeecstud.signals.feature_auth.data.network.mapper.UserLoginEntityRequestDtoMapper
import com.girrafeecstud.signals.feature_auth.data.network.mapper.UserLoginResponseDtoUUIDMapper
import com.girrafeecstud.signals.feature_auth.domain.entity.UserLoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.*
import javax.inject.Inject

class UserLoginDataSourceImpl @Inject constructor(
    private val api: LoginApi,
    private val requestMapper: UserLoginEntityRequestDtoMapper,
    private val responseMapper: UserLoginResponseDtoUUIDMapper
): UserLoginDataSource {

    // TODO вопрос про закрытие соединения с сетью в блоке finally
    override suspend fun login(user: UserLoginEntity): Flow<BusinessResult<UUID>> {
        val loginRequest = requestMapper.map(user)

        return flow {

            try {

                val response = api.login(loginRequest = loginRequest)
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    emit(BusinessResult.Success(_data = responseMapper.map(responseBody)))
                } else {
                    emit(BusinessResult.Error(businessErrorType = BusinessErrorType.WRONG_LOGIN_DATA))
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