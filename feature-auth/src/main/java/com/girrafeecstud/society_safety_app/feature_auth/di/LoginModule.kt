package com.girrafeecstud.society_safety_app.feature_auth.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_auth.data.network.mapper.UserLoginEntityRequestDtoMapper
import com.girrafeecstud.society_safety_app.feature_auth.data.network.mapper.UserLoginResponseDtoUUIDMapper
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.LoginScope
import com.girrafeecstud.society_safety_app.feature_auth.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [LoginModule.LoginBindModule::class])
class LoginModule {

    @Provides
    @LoginScope
    fun provideUserLoginEntityRequestDtoMapper() = UserLoginEntityRequestDtoMapper()

    @Provides
    @LoginScope
    fun provideUserLoginResponseDtoUUIDMapper() = UserLoginResponseDtoUUIDMapper()

    @Module
    interface LoginBindModule {
        @Binds
        @LoginScope
        @ViewModelKey(LoginViewModel::class)
        @IntoMap
        fun bindLoginViewModel(impl: LoginViewModel): ViewModel
    }

}