package com.girrafeecstud.society_safety_app.feature_map.di

import com.girrafeecstud.society_safety_app.core_base.presentation.base.di.BaseViewModelFactoryModule
import com.girrafeecstud.society_safety_app.core_preferences.di.CorePreferencesApi
import com.girrafeecstud.society_safety_app.feature_map.di.dependencies.MainDependencies
import com.girrafeecstud.society_safety_app.feature_map.ui.MainFlowFragment
import dagger.Component


// TODO переименовать потом
@Component(
    modules = [
        MainModule::class,
        BaseViewModelFactoryModule::class
    ],
    dependencies = [
        MainDependencies::class
    ]
)
interface MainComponent {

    fun inject(fragment: MainFlowFragment)

    @Component.Builder
    interface Builder {

        fun build(): MainComponent

        fun mainComponentDependencies(dependencies: MainDependencies): Builder

    }

    companion object {

        private var _mainComponent: MainComponent? = null

        val mainComponent get() = _mainComponent!!

        fun init(dependencies: MainDependencies) {
            if (_mainComponent == null)
                _mainComponent = DaggerMainComponent
                    .builder()
                    .mainComponentDependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _mainComponent = null
        }

    }

    @Component(
        dependencies = [
            CorePreferencesApi::class
        ]
    )
    interface MainDependenciesComponent: MainDependencies

}