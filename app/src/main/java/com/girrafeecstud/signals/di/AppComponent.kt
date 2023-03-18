package com.girrafeecstud.signals.di

import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.core_base.presentation.base.di.BaseViewModelFactoryModule
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