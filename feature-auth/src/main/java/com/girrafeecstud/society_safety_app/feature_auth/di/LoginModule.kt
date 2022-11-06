package com.girrafeecstud.society_safety_app.feature_auth.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.LoginScope
import com.girrafeecstud.society_safety_app.feature_auth.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [LoginModule.LoginBindModule::class])
class LoginModule {

    @Module
    interface LoginBindModule {
        @Binds
        @LoginScope
        @ViewModelKey(LoginViewModel::class)
        @IntoMap
        fun bindLoginViewModel(impl: LoginViewModel): ViewModel
    }

}