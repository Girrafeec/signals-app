package com.girrafeecstud.society_safety_app.di

import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_network.data.di.module.NetworkModule
import com.girrafeecstud.society_safety_app.feature_auth.di.provider.AuthComponentProvider
import com.girrafeecstud.society_safety_app.core_base.presentation.base.di.BaseViewModelFactoryModule
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_impl.engine.SosSignalEngineImpl
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        BaseViewModelFactoryModule::class
              ],
    dependencies = [
        AppDependencies::class
    ]
)
interface AppComponent {

    fun mainViewModelFactody(): MainViewModelFactory

    fun sosSignalEngine(): SosSignalEngineImpl

    @Component.Builder
    interface Builder {

        fun appDependencies(appDependencies: AppDependencies): Builder

        fun build(): AppComponent
    }

}