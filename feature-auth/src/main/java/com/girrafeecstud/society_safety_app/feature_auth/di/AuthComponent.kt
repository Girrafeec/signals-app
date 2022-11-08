package com.girrafeecstud.society_safety_app.feature_auth.di

import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.society_safety_app.core_base.presentation.base.di.BaseViewModelFactoryModule
import com.girrafeecstud.society_safety_app.core_network.data.di.CoreNetworkApi
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import com.girrafeecstud.society_safety_app.feature_auth.di.dependencies.AuthDependencies
import com.girrafeecstud.society_safety_app.feature_auth.ui.AuthFlowFragment
import dagger.Component

@AuthScope
@Component(
    modules = [
        AuthModule::class,
        BaseViewModelFactoryModule::class // Разобраться с этим модулем потом
    ],
    dependencies = [
        AuthDependencies::class
    ]
)
interface AuthComponent {

    fun inject(fragment: AuthFlowFragment)

    fun loginComponent(): LoginComponent.Builder

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
//            CoreBaseApi::class
        ]
    )
    interface AuthDependenciesComponent: AuthDependencies { }

}