package com.girrafeecstud.push_notifications_impl.data

import com.girrafeecstud.push_notifications_impl.data.network.NotificationTokenRequestDto
import com.girrafeecstud.push_notifications_impl.data.network.NotificationTokensApi
import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.base.NoNetworkConnectionException
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteNotificationTokensDataSource @Inject constructor(
    private val api: NotificationTokensApi
) {

    suspend fun setNotificationToken(userAuthToken: String, notificationToken: String): Flow<BusinessResult<EmptyResult>> =
        flow {
            val requestBody = NotificationTokenRequestDto(notificationToken = notificationToken)

            try {
                val response = api.updateNotificationToken(authorizationToken = userAuthToken, body = requestBody)
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    emit(BusinessResult.Success(_data = EmptyResult))
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
