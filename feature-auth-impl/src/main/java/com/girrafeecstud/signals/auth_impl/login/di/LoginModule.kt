package com.girrafeecstud.signals.auth_impl.login.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.auth_impl.login.data.*
import com.girrafeecstud.signals.auth_impl.login.data.datasource.ILoginDataSource
import com.girrafeecstud.signals.auth_impl.login.data.datasource.LoginDataSource
import com.girrafeecstud.signals.auth_impl.login.data.network.LoginApi
import com.girrafeecstud.signals.auth_impl.login.data.network.UserLoginEntityRequestDtoMapper
import com.girrafeecstud.signals.auth_impl.login.data.repository.LoginRepository
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.auth_impl.login.domain.ILoginRepository
import com.girrafeecstud.signals.auth_impl.login.domain.LoginUseCase
import com.girrafeecstud.signals.auth_impl.login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [LoginModule.LoginBindModule::class])
class LoginModule {

    @Provides
    @LoginScope
    fun getLoginApi(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)

    @Provides
    @LoginScope
    fun provideUserLoginEntityRequestDtoMapper() = UserLoginEntityRequestDtoMapper()

    @Provides
    @LoginScope
    fun provideUserLoginUseCase(repository: ILoginRepository): LoginUseCase =
        LoginUseCase(repository = repository)

    @Module
    interface LoginBindModule {

        @Binds
        @LoginScope
        fun bindLoginDataSource(impl: LoginDataSource): ILoginDataSource

        @Binds
        @LoginScope
        fun bindLoginRepositoryImpl(impl: LoginRepository): ILoginRepository

        @Binds
        @LoginScope
        @ViewModelKey(LoginViewModel::class)
        @IntoMap
        fun bindLoginViewModel(impl: LoginViewModel): ViewModel
    }

}