package com.girrafeecstud.society_safety_app.feature_auth.data.di

import com.girrafeecstud.society_safety_app.feature_auth.data.repository.UserRegistrationRepositoryImpl
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.RegistrationScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserRegistrationRepository
import dagger.Binds
import dagger.Module

@Module(includes = [RegistrationRepositoryModule.RepositoryBindModule::class])
class RegistrationRepositoryModule {

    @Module
    interface RepositoryBindModule {

        @Binds
        @RegistrationScope
        fun bindUserRegistrationRepositoryImpl(impl: UserRegistrationRepositoryImpl): UserRegistrationRepository

    }

}