package com.girrafeecstud.push_notifications_impl.data

import android.util.Log
import com.girrafeecstud.push_notifications_api.data.INotificationTokensDataSource
import com.girrafeecstud.push_notifications_api.data.INotificationTokensRepository
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NotificationTokensRepository @Inject constructor(
    private val localPushNotificationsDataSource: INotificationTokensDataSource,
    private val remotePushNotificationsDataSource: RemoteNotificationTokensDataSource,
    private val authDataSource: IAuthDataSource
) : INotificationTokensRepository {

    override suspend fun saveNotificationToken(notificationToken: String) {
        localPushNotificationsDataSource.setNotificationToken(notificationToken)
        localPushNotificationsDataSource.setNotificationTokenNotSent()
    }

    override suspend fun isNotificationTokenSent(): Boolean =
        localPushNotificationsDataSource.isNotificationTokenSent()

    override suspend fun sendNotificationToken(): Flow<BusinessResult<EmptyResult>> =
        authDataSource.getUserToken()
            .flatMapLatest { authTokenResult ->
                when (authTokenResult) {
                    is BusinessResult.Success -> {
                        localPushNotificationsDataSource.getNotificationToken()
                            .flatMapLatest { localNotificationTokenResult ->
                                when (localNotificationTokenResult) {
                                    is BusinessResult.Success -> {
                                        Log.i("tag notifications", "auth token ${authTokenResult._data}")
                                        remotePushNotificationsDataSource.setNotificationToken(
                                            userAuthToken = authTokenResult._data!!,
                                            notificationToken = localNotificationTokenResult._data!!
                                        )
                                    }
                                    is BusinessResult.Error -> { flowOf(BusinessResult.Error(localNotificationTokenResult.businessErrorType)) }
                                    is BusinessResult.Exception -> { flowOf(BusinessResult.Exception(localNotificationTokenResult.exceptionType)) }
                                }
                            }
                    }
                    is BusinessResult.Error -> { flowOf(BusinessResult.Error(authTokenResult.businessErrorType)) }
                    is BusinessResult.Exception -> { flowOf(BusinessResult.Exception(authTokenResult.exceptionType)) }
                }
            }
}
