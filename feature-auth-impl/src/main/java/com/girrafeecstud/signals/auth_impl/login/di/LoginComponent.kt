package com.girrafeecstud.signals.auth_impl.login.di

import com.girrafeecstud.signals.auth_impl.login.ui.LoginFragment
import dagger.Subcomponent

@LoginScope
@Subcomponent(
    modules = [
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