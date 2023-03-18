package com.girrafeecstud.signals.feature_auth.data.di

import com.girrafeecstud.signals.feature_auth.data.repository.UserLoginRepositoryImpl
import com.girrafeecstud.signals.feature_auth.di.annotation.LoginScope
import com.girrafeecstud.signals.feature_auth.domain.repository.UserLoginRepository
import dagger.Binds
import dagger.Module

@Module(includes = [LoginRepositoryModule.RepositoryBindModule::class])
class LoginRepositoryModule {

    @Module
    interface RepositoryBindModule {

        @Binds
        @LoginScope
        fun bindUserLoginRepositoryImpl(impl: UserLoginRepositoryImpl): UserLoginRepository

    }

}