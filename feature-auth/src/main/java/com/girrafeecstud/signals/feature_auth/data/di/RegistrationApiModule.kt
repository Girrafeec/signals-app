package com.girrafeecstud.signals.feature_auth.data.di

import com.girrafeecstud.signals.feature_auth.data.network.api.RegistrationApi
import com.girrafeecstud.signals.feature_auth.di.annotation.RegistrationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RegistrationApiModule {
    @Provides
    @RegistrationScope
    fun getRegistrationApi(retrofit: Retrofit): RegistrationApi = retrofit.create(RegistrationApi::class.java)
}