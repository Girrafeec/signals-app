package com.girrafeecstud.signals.auth_impl.di

import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.auth_api.data.IAuthRepository
import com.girrafeecstud.signals.auth_impl.auth_common.data.AuthSharedPreferencesDataSource
import com.girrafeecstud.signals.auth_impl.auth_common.data.AuthSharedPreferencesRepository
import com.girrafeecstud.signals.auth_impl.login.di.LoginComponent
import com.girrafeecstud.signals.auth_impl.registration.di.RegistrationComponent
import dagger.Binds
import dagger.Module

@Module(
    includes = [AuthModule.AuthBindModule::class],
    subcomponents = [
        LoginComponent::class,
        RegistrationComponent::class
    ]
)
class AuthModule {

    @Module
    interface AuthBindModule {

        @AuthScope
        @Binds
        fun bindAuthSharedPreferencesDataSource(impl: AuthSharedPreferencesDataSource): IAuthDataSource

        @AuthScope
        @Binds
        fun bindIAuthSharedPreferencesRepository(impl: AuthSharedPreferencesRepository): IAuthRepository

    }

}