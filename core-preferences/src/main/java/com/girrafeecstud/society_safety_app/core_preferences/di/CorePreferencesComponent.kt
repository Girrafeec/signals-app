package com.girrafeecstud.society_safety_app.core_preferences.di

import com.girrafeecstud.society_safety_app.core_preferences.data.di.PreferencesDataModule
import com.girrafeecstud.society_safety_app.core_preferences.di.dependencies.CorePreferencesDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PreferencesDataModule::class
    ],
    dependencies = [
        CorePreferencesDependencies::class
    ]
)
interface CorePreferencesComponent: CorePreferencesApi {

    @Component.Builder
    interface Builder {

        fun corePreferencesDependencies(dependencies: CorePreferencesDependencies): Builder

        fun build(): CorePreferencesComponent

    }

    companion object {

        private var _corePreferencesComponent: CorePreferencesComponent? = null

        val corePreferencesComponent get() = _corePreferencesComponent!!

        fun init(preferencesDependencies: CorePreferencesDependencies) {
            if (_corePreferencesComponent == null)
                _corePreferencesComponent = DaggerCorePreferencesComponent
                    .builder()
                    .corePreferencesDependencies(dependencies = preferencesDependencies)
                    .build()
        }

        fun reset() {
            _corePreferencesComponent = null
        }

    }

}