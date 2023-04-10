package com.girrafeecstud.signals.di

import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.core_base.presentation.base.di.BaseViewModelFactoryModule
import com.girrafeecstud.sos_signal_impl.engine.SosSignalEngineImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
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

    fun OnBoardDataSource(): OnBoardSharedPreferencesDataSource

    fun sosSignalEngine(): SosSignalEngineImpl

    @Component.Builder
    interface Builder {

        fun appDependencies(appDependencies: AppDependencies): Builder

        fun build(): AppComponent
    }

    companion object {

        private var _appComponent: AppComponent? = null

        val appComponent get() = _appComponent!!

        fun init(dependencies: AppDependencies) {
            if (_appComponent == null)
                _appComponent = DaggerAppComponent
                    .builder()
                    .appDependencies(appDependencies = dependencies)
                    .build()
        }

        fun reset() {
            _appComponent = null
        }

    }

}