package com.girrafeecstud.society_safety_app.feature_auth.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_auth.data.di.LoginApiModule
import com.girrafeecstud.society_safety_app.feature_auth.data.di.LoginDataSourceModule
import com.girrafeecstud.society_safety_app.feature_auth.data.di.LoginRepositoryModule
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.LoginScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.di.LoginUseCaseModule
import com.girrafeecstud.society_safety_app.feature_auth.presentation.LoginViewModel
import com.girrafeecstud.society_safety_app.feature_auth.ui.LoginFragment
import dagger.Binds
import dagger.Provides
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(
    modules = [
        LoginApiModule::class,
        LoginDataSourceModule::class,
        LoginRepositoryModule::class,
        LoginUseCaseModule::class,
        LoginModule::class
    ]
)
@LoginScope
interface LoginComponent {

    fun inject(fragment: LoginFragment)

}