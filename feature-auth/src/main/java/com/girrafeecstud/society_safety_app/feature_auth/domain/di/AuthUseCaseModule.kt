package com.girrafeecstud.society_safety_app.feature_auth.domain.di

import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserLoginRepository
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserRegistrationRepository
import com.girrafeecstud.society_safety_app.feature_auth.domain.usecase.UserLoginUseCase
import com.girrafeecstud.society_safety_app.feature_auth.domain.usecase.UserRegistratonUseCase
import dagger.Module
import dagger.Provides

@Module
class AuthUseCaseModule {

    // TODO добавить скоуп для модуля аутентификации
    @Provides
    @AuthScope
    fun provideUserLoginUseCase(repository: UserLoginRepository): UserLoginUseCase =
        UserLoginUseCase(repository = repository)


    // TODO добавить скоуп для регистрации
    @Provides
    @AuthScope
    fun provideUserRegistrationUseCase(repository: UserRegistrationRepository) =
        UserRegistratonUseCase(repository = repository)

}