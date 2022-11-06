package com.girrafeecstud.society_safety_app.feature_auth.data.di

import com.girrafeecstud.society_safety_app.core_network.data.di.module.NetworkModule
import com.girrafeecstud.society_safety_app.feature_auth.data.network.api.LoginApi
import com.girrafeecstud.society_safety_app.feature_auth.data.network.api.RegistrationApi
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class AuthApiModule {

    // TODO поменять scope
    @Provides
    @AuthScope
    fun getLoginApi(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)

    // TODO поменять scope
    @Provides
    @AuthScope
    fun getRegistrationApi(retrofit: Retrofit): RegistrationApi = retrofit.create(RegistrationApi::class.java)

}