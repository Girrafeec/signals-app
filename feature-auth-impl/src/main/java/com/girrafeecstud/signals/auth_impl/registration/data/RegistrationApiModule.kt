package com.girrafeecstud.signals.auth_impl.registration.data

import com.girrafeecstud.signals.auth_impl.registration.di.RegistrationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RegistrationApiModule {
    @Provides
    @RegistrationScope
    fun getRegistrationApi(retrofit: Retrofit): RegistrationApi = retrofit.create(RegistrationApi::class.java)
}