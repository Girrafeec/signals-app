package com.girrafeecstud.society_safety_app.feature_auth.di

import com.girrafeecstud.society_safety_app.feature_auth.data.di.LoginApiModule
import com.girrafeecstud.society_safety_app.feature_auth.data.di.LoginDataSourceModule
import com.girrafeecstud.society_safety_app.feature_auth.data.di.LoginRepositoryModule
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.LoginScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.di.LoginUseCaseModule
import com.girrafeecstud.society_safety_app.feature_auth.ui.LoginFragment
import dagger.Subcomponent

@LoginScope
@Subcomponent(
    modules = [
        LoginApiModule::class,
        LoginDataSourceModule::class,
        LoginRepositoryModule::class,
        LoginUseCaseModule::class,
        LoginModule::class
    ]
)
interface LoginComponent {

    fun inject(fragment: LoginFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): LoginComponent

    }

}