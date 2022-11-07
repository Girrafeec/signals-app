package com.girrafeecstud.society_safety_app.feature_auth.data.di

import com.girrafeecstud.society_safety_app.feature_auth.data.network.api.LoginApi
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.LoginScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginApiModule {
    @Provides
    @LoginScope
    fun getLoginApi(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)
}