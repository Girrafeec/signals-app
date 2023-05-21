/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_api.data

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow

interface INotificationTokensRepository {

    suspend fun saveNotificationToken(notificationToken: String)

    suspend fun isNotificationTokenSent(): Boolean

    suspend fun sendNotificationToken(): Flow<BusinessResult<EmptyResult>>

}