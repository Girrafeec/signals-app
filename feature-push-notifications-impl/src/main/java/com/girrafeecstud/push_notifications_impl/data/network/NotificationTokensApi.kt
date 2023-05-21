/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_impl.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface NotificationTokensApi {

    @PATCH("notification-tokens")
    suspend fun updateNotificationToken(
        @Header("Authorization") authorizationToken: String,
        @Body body: NotificationTokenRequestDto
    ) : Response<Unit>
}