/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_api.di

import com.girrafeecstud.push_notifications_api.data.INotificationTokensDataSource
import com.girrafeecstud.push_notifications_api.data.INotificationTokensRepository
import com.girrafeecstud.push_notifications_api.engine.INotificationTokensEngine

interface PushNotificationsFeatureApi {

    fun getNotificationTokensDataSource(): INotificationTokensDataSource

    fun getNotificationTokensRepository(): INotificationTokensRepository

    fun getNotificationTokensEngine(): INotificationTokensEngine

}