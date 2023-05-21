/* Created by Girrafeec */

package com.girrafeecstud.signals.auth_impl.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.signals.auth_api.di.AuthFeatureApi
import com.girrafeecstud.signals.auth_impl.login.di.LoginComponent
import com.girrafeecstud.signals.auth_impl.registration.di.RegistrationComponent
import com.girrafeecstud.signals.auth_impl.ui.AuthFlowFragment
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkApi
import dagger.Component

@AuthScope
@Component(
    modules = [
        AuthModule::class
    ],
    dependencies = [
        AuthFeatureDependencies::class
    ]
)
interface AuthFeatureComponent : AuthFeatureApi {

    fun inject(fragment: AuthFlowFragment)

    fun loginComponent(): LoginComponent.Builder

    fun registrationComponent(): RegistrationComponent.Builder

    @Component.Builder
    interface Builder {

        fun authDependencies(authDependencies: AuthFeatureDependencies): Builder

        fun build(): AuthFeatureComponent
    }

    companion object {

        private var _authFeatureComponent: AuthFeatureComponent? = null

        val authComponent get() = _authFeatureComponent!!

        fun init(authDependencies: AuthFeatureDependencies) {
            if (_authFeatureComponent == null)
                _authFeatureComponent = DaggerAuthFeatureComponent
                    .builder()
                    .authDependencies(authDependencies = authDependencies)
                    .build()
        }

        fun reset() {
            _authFeatureComponent = null
        }

    }

    @AuthScope
    @Component(
        dependencies = [
            CoreNetworkApi::class,
            CoreComponentsApi::class
        ]
    )
    interface AuthDependenciesComponent: AuthFeatureDependencies

}