package com.girrafeecstud.signals.auth_impl.registration.data

import com.girrafeecstud.signals.auth_impl.registration.di.RegistrationScope
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