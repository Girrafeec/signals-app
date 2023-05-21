package com.girrafeecstud.signals.auth_impl.registration.di

import com.girrafeecstud.signals.auth_impl.registration.domain.UserRegistrationRepository
import com.girrafeecstud.signals.auth_impl.registration.domain.UserRegistratonUseCase
import dagger.Module
import dagger.Provides

@Module
class RegistrationUseCaseModule {

    @Provides
    @RegistrationScope
    fun provideUserRegistrationUseCase(repository: UserRegistrationRepository) =
        UserRegistratonUseCase(repository = repository)

}