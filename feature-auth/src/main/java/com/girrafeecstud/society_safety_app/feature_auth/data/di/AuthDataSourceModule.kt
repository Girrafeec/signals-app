package com.girrafeecstud.society_safety_app.feature_auth.data.di

import com.girrafeecstud.society_safety_app.feature_auth.data.datasource.UserLoginDataSource
import com.girrafeecstud.society_safety_app.feature_auth.data.datasource.UserLoginDataSourceImpl
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import dagger.Binds
import dagger.Module


@Module(includes = [AuthDataSourceModule.DataSourceBindModule::class])
class AuthDataSourceModule {

    @Module
    interface DataSourceBindModule {

        // TODO поменять скоуп на аутентификацию
        @Binds
        @AuthScope
        fun bindLoginDataSourceImpl(impl: UserLoginDataSourceImpl): UserLoginDataSource

    }

}