/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_api.data

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface INotificationTokensDataSource {

    suspend fun isNotificationTokenSent(): Boolean

    suspend fun setNotificationTokenSent()

    suspend fun setNotificationTokenNotSent()

    suspend fun getNotificationToken(): Flow<BusinessResult<String>>

    suspend fun setNotificationToken(notificationToken: String)

}