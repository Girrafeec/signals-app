package com.girrafeecstud.society_safety_app.feature_auth.di

import com.girrafeecstud.society_safety_app.core_base.presentation.base.di.BaseViewModelFactoryModule
import com.girrafeecstud.society_safety_app.core_network.data.network.NetworkConfig
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import com.girrafeecstud.society_safety_app.feature_auth.di.dependencies.AuthDependencies
import com.girrafeecstud.society_safety_app.feature_auth.ui.AuthFlowFragment
import dagger.Component

@AuthScope
@Component(
    modules = [
        AuthModule::class,
        BaseViewModelFactoryModule::class //TODO разобраться с этим модулем
    ],
    dependencies = [
        AuthDependencies::class
    ]
)
interface AuthComponent {

    fun inject(fragment: AuthFlowFragment)

    fun loginComponent(): LoginComponent.Builder

    @Component.Builder
    interface Builder {

        fun authDependencies(authDependencies: AuthDependencies): Builder

        fun build(): AuthComponent
    }


    @AuthScope
    @Component(
        dependencies = [NetworkConfig::class]
    )
    interface AuthDependenciesComponent: AuthDependencies

}