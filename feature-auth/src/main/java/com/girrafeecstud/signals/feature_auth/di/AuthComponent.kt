package com.girrafeecstud.signals.feature_auth.di

import com.girrafeecstud.signals.core_base.presentation.base.di.BaseViewModelFactoryModule
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkApi
import com.girrafeecstud.signals.core_preferences.di.CorePreferencesApi
import com.girrafeecstud.signals.feature_auth.di.annotation.AuthScope
import com.girrafeecstud.signals.feature_auth.di.dependencies.AuthDependencies
import com.girrafeecstud.signals.feature_auth.ui.AuthFlowFragment
import dagger.Component

@AuthScope
@Component(
    modules = [
        AuthModule::class,
        BaseViewModelFactoryModule::class // TODO разобраться с этим
    ],
    dependencies = [
        AuthDependencies::class
    ]
)
interface AuthComponent {

    fun inject(fragment: AuthFlowFragment)

    fun loginComponent(): LoginComponent.Builder

    fun registrationComponent(): RegistrationComponent.Builder

//    fun mainViewModelFactory(): MainViewModelFactory

    @Component.Builder
    interface Builder {

        fun authDependencies(authDependencies: AuthDependencies): Builder

        fun build(): AuthComponent
    }

    companion object {

        private var _authComponent: AuthComponent? = null

        val authComponent get() = _authComponent!!

        fun init(authDependencies: AuthDependencies) {
            if (_authComponent == null)
                _authComponent = DaggerAuthComponent
                    .builder()
                    .authDependencies(authDependencies = authDependencies)
                    .build()
        }

        fun reset() {
            _authComponent = null
        }

    }

    @AuthScope
    @Component(
        dependencies = [
            CoreNetworkApi::class,
            CorePreferencesApi::class
//            CoreBaseApi::class
        ]
    )
    interface AuthDependenciesComponent: AuthDependencies { }

}