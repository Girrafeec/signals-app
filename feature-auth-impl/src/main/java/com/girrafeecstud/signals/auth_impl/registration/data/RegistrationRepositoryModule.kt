package com.girrafeecstud.signals.auth_impl.registration.data

import com.girrafeecstud.signals.auth_impl.registration.di.RegistrationScope
import com.girrafeecstud.signals.auth_impl.registration.domain.UserRegistrationRepository
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