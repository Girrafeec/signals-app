package com.girrafeecstud.signals.di

import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource
import com.girrafeecstud.push_notifications_api.data.INotificationTokensDataSource
import com.girrafeecstud.push_notifications_impl.data.LocalNotificationTokensDataSource
import com.girrafeecstud.signals.auth_api.data.IAuthRepository
import com.girrafeecstud.signals.auth_impl.auth_common.data.AuthSharedPreferencesDataSource
import com.girrafeecstud.signals.auth_impl.auth_common.data.AuthSharedPreferencesRepository
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.core_base.presentation.base.di.BaseViewModelFactoryModule
import com.girrafeecstud.signals.rescuer_mode_impl.engine.RescuerModeEngine
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

    fun onBoardDataSource(): OnBoardSharedPreferencesDataSource

    fun sosSignalEngine(): SosSignalEngineImpl

    fun authDataSource(): AuthSharedPreferencesDataSource

    fun notificationTokensDataSource(): LocalNotificationTokensDataSource

    fun rescuerModeEngine(): RescuerModeEngine

//    fun authRepository(): AuthSharedPreferencesRepository

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