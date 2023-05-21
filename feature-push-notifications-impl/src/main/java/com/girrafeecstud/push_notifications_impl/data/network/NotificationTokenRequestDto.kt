/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_impl.data.network

import com.google.gson.annotations.SerializedName

data class NotificationTokenRequestDto(
    @SerializedName("notificationToken")
    val notificationToken: String
)