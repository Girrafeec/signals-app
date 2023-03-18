package com.girrafeecstud.signals.feature_auth.domain.di

import com.girrafeecstud.signals.feature_auth.di.annotation.RegistrationScope
import com.girrafeecstud.signals.feature_auth.domain.repository.UserRegistrationRepository
import com.girrafeecstud.signals.feature_auth.domain.usecase.UserRegistratonUseCase
import dagger.Module
import dagger.Provides

@Module
class RegistrationUseCaseModule {

    @Provides
    @RegistrationScope
    fun provideUserRegistrationUseCase(repository: UserRegistrationRepository) =
        UserRegistratonUseCase(repository = repository)

}