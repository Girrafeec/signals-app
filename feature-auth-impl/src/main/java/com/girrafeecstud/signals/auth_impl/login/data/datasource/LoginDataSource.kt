package com.girrafeecstud.signals.auth_impl.login.data.datasource

import android.util.Log
import com.girrafeecstud.signals.auth_impl.login.data.network.UserLoginEntityRequestDtoMapper
import com.girrafeecstud.signals.auth_impl.login.data.network.LoginApi
import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.core_base.domain.base.BusinessErrorType
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.auth_impl.login.domain.UserLoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.*
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val api: LoginApi,
    private val requestMapper: UserLoginEntityRequestDtoMapper,
): ILoginDataSource {

    override fun login(user: UserLoginEntity): Flow<BusinessResult<String>> {
        val loginRequest = requestMapper.map(user)

        return flow {

            try {

                val response = api.login(loginRequest = loginRequest)
                val responseBody = response.body()

                Log.i("login ds", "response code ${response.code()}")

                if (response.isSuccessful && responseBody != null) {
                    emit(BusinessResult.Success(_data = responseBody))
                } else {
                    emit(BusinessResult.Error(businessErrorType = BusinessErrorType.WRONG_LOGIN_DATA))
                }
            } catch (exception: NoNetworkConnectionException) {
                emit(BusinessResult.Exception(exceptionType = ExceptionType.NO_INTERNET_CONNECTION))
            } catch (exception: SocketTimeoutException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }catch (exception: IOException) {
                exception.printStackTrace()
                emit(BusinessResult.Exception(exceptionType = ExceptionType.INTERNET_CONNECTION_TIMEOUT))
            }

        }
    }

}