package com.girrafeecstud.society_safety_app.feature_auth.data.di

import com.girrafeecstud.society_safety_app.feature_auth.data.datasource.UserLoginDataSource
import com.girrafeecstud.society_safety_app.feature_auth.data.datasource.UserLoginDataSourceImpl
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.LoginScope
import dagger.Binds
import dagger.Module


@Module(includes = [LoginDataSourceModule.DataSourceBindModule::class])
class LoginDataSourceModule {

    @Module
    interface DataSourceBindModule {

        @Binds
        @LoginScope
        fun bindLoginDataSourceImpl(impl: UserLoginDataSourceImpl): UserLoginDataSource

    }

}