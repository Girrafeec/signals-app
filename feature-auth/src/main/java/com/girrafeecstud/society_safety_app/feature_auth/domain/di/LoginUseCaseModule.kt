package com.girrafeecstud.society_safety_app.feature_auth.domain.di

import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.LoginScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserLoginRepository
import com.girrafeecstud.society_safety_app.feature_auth.domain.usecase.UserLoginUseCase
import dagger.Module
import dagger.Provides

@Module
class LoginUseCaseModule {

    @Provides
    @LoginScope
    fun provideUserLoginUseCase(repository: UserLoginRepository): UserLoginUseCase =
        UserLoginUseCase(repository = repository)

}
