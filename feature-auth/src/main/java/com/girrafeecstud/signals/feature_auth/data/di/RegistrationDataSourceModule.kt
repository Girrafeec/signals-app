package com.girrafeecstud.signals.feature_auth.data.di

import com.girrafeecstud.signals.feature_auth.data.datasource.UserRegistrationDataSource
import com.girrafeecstud.signals.feature_auth.data.datasource.UserRegistrationDataSourceImpl
import com.girrafeecstud.signals.feature_auth.di.annotation.RegistrationScope
import dagger.Binds
import dagger.Module


@Module(includes = [RegistrationDataSourceModule.DataSourceBindModule::class])
class RegistrationDataSourceModule {

    @Module
    interface DataSourceBindModule {

        @Binds
        @RegistrationScope
        fun bindRegistrationDataSourceImpl(impl: UserRegistrationDataSourceImpl): UserRegistrationDataSource

    }

}