package com.girrafeecstud.society_safety_app.feature_auth.domain.di

import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.RegistrationScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserRegistrationRepository
import com.girrafeecstud.society_safety_app.feature_auth.domain.usecase.UserRegistratonUseCase
import dagger.Module
import dagger.Provides

@Module
class RegistrationUseCaseModule {

    @Provides
    @RegistrationScope
    fun provideUserRegistrationUseCase(repository: UserRegistrationRepository) =
        UserRegistratonUseCase(repository = repository)

}