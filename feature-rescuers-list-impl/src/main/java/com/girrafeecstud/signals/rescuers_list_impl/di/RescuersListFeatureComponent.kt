package com.girrafeecstud.signals.rescuers_list_impl.di

import com.girrafeecstud.signals.rescuers_api.di.RescuersFeatureApi
import com.girrafeecstud.signals.rescuers_list_api.di.RescuersListFeatureApi
import com.girrafeecstud.signals.rescuers_list_impl.di.annotation.RescuersListFeatureScope
import com.girrafeecstud.signals.rescuers_list_impl.ui.RescuersListFragmentImpl
import dagger.Component

@RescuersListFeatureScope
@Component(
    modules = [RescuersListFeatureModule::class],
    dependencies = [RescuersListFeatureDependencies::class]
)
interface RescuersListFeatureComponent : RescuersListFeatureApi {

    fun inject(fragment: RescuersListFragmentImpl)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: RescuersListFeatureDependencies): Builder

        fun build(): RescuersListFeatureComponent

    }

    companion object {

        private var _rescuersListFeatureComponent: RescuersListFeatureComponent? = null

        val rescuersListFeatureComponent get() = _rescuersListFeatureComponent!!

        fun init(dependencies: RescuersListFeatureDependencies) {
            if (_rescuersListFeatureComponent == null)
                _rescuersListFeatureComponent = DaggerRescuersListFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _rescuersListFeatureComponent = null
        }

    }

    @RescuersListFeatureScope
    @Component(
        dependencies = [RescuersFeatureApi::class]
    )
    interface RescuersListFeatureDependenciesComponent : RescuersListFeatureDependencies

}