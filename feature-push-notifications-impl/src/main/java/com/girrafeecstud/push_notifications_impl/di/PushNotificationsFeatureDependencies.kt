/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_impl.di

import android.content.Context
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import retrofit2.Retrofit

interface PushNotificationsFeatureDependencies {

    fun getRetrofit(): Retrofit

    fun getContext(): Context

    fun getAuthDataSource(): IAuthDataSource

}