package com.girrafeecstud.push_notifications_impl.di

import com.girrafeecstud.push_notifications_api.data.INotificationTokensDataSource
import com.girrafeecstud.push_notifications_api.data.INotificationTokensRepository
import com.girrafeecstud.push_notifications_api.engine.INotificationTokensEngine
import com.girrafeecstud.push_notifications_impl.data.LocalNotificationTokensDataSource
import com.girrafeecstud.push_notifications_impl.data.NotificationTokensRepository
import com.girrafeecstud.push_notifications_impl.data.RemoteNotificationTokensDataSource
import com.girrafeecstud.push_notifications_impl.data.network.NotificationTokensApi
import com.girrafeecstud.push_notifications_impl.engine.NotificationTokensEngine
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [PushNotificationsFeatureModule.PushNotificationsFeatureBindModule::class])
class PushNotificationsFeatureModule {

    @PushNotificationsScope
    @Provides
    fun provideNotificationTokensApi(retrofit: Retrofit) = retrofit.create(NotificationTokensApi::class.java)

    @PushNotificationsScope
    @Provides
    fun provideRemoteNotificationTokensDataSource(api: NotificationTokensApi) =
        RemoteNotificationTokensDataSource(api = api)

    @Module
    interface PushNotificationsFeatureBindModule {

        @PushNotificationsScope
        @Binds
        fun bindNotificationTokensDataSource(impl: LocalNotificationTokensDataSource): INotificationTokensDataSource

        @PushNotificationsScope
        @Binds
        fun bindNotificationTokensRepository(impl: NotificationTokensRepository): INotificationTokensRepository

        @PushNotificationsScope
        @Binds
        fun bindNotificationTokensEngine(impl: NotificationTokensEngine): INotificationTokensEngine

    }

}
