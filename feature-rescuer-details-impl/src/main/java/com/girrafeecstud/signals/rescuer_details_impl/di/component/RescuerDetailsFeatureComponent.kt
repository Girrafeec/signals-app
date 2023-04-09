package com.girrafeecstud.signals.rescuer_details_impl.di.component

import com.girrafeecstud.signals.rescuer_details_api.di.RescuerDetailsFeatureApi
import com.girrafeecstud.signals.rescuer_details_impl.di.RescuerDetailsFeatureDependencies
import com.girrafeecstud.signals.rescuer_details_impl.di.annotation.RescuerDetailsFeatureScope
import com.girrafeecstud.signals.rescuer_details_impl.di.module.RescuerDetailsFeatureModule
import com.girrafeecstud.signals.rescuer_details_impl.ui.RescuerDetailsParentFragment
import com.girrafeecstud.signals.rescuers_api.di.RescuersFeatureApi
import dagger.Component

@RescuerDetailsFeatureScope
@Component(
    modules = [RescuerDetailsFeatureModule::class],
    dependencies = [RescuerDetailsFeatureDependencies::class]
)
interface RescuerDetailsFeatureComponent : RescuerDetailsFeatureApi {

    fun inject(fragment: RescuerDetailsParentFragment)

    fun rescuerDetailsComponent(): RescuerDetailsComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: RescuerDetailsFeatureDependencies): Builder

        fun build(): RescuerDetailsFeatureComponent

    }

    companion object {

        private var _rescuerDetailsFeatureComponent: RescuerDetailsFeatureComponent? = null

        val rescuerDetailsFeatureComponent get() = _rescuerDetailsFeatureComponent!!

        fun init(dependencies: RescuerDetailsFeatureDependencies) {
            if (_rescuerDetailsFeatureComponent == null) {
                _rescuerDetailsFeatureComponent = DaggerRescuerDetailsFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
            }
        }

        fun reset() {
            _rescuerDetailsFeatureComponent = null
        }

    }

    @RescuerDetailsFeatureScope
    @Component(
        dependencies = [
            RescuersFeatureApi::class
        ]
    )
    interface RescuerDetailsFeatureDependenciesComponent : RescuerDetailsFeatureDependencies

}