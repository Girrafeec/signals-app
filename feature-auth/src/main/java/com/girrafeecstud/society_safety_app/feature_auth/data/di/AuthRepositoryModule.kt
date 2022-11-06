package com.girrafeecstud.society_safety_app.feature_auth.data.di

import com.girrafeecstud.society_safety_app.feature_auth.data.repository.UserLoginRepositoryImpl
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserLoginRepository
import dagger.Binds
import dagger.Module

@Module(includes = [AuthRepositoryModule.RepositoryBindModule::class])
class AuthRepositoryModule {

    @Module
    interface RepositoryBindModule {

        // TODO поменять скоуп на аутентификацию
        @Binds
        @AuthScope
        fun bindUserLoginRepositoryImpl(impl: UserLoginRepositoryImpl): UserLoginRepository

    }

}