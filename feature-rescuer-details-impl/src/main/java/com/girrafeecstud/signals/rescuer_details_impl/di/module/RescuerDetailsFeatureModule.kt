package com.girrafeecstud.signals.rescuer_details_impl.di.module

import com.girrafeecstud.signals.rescuer_details_api.ui.BaseRescuerDetailsFragment
import com.girrafeecstud.signals.rescuer_details_impl.di.component.RescuerDetailsComponent
import com.girrafeecstud.signals.rescuer_details_impl.di.annotation.RescuerDetailsFeatureScope
import com.girrafeecstud.signals.rescuer_details_impl.ui.RescuerDetailsFragment
import com.girrafeecstud.signals.rescuer_details_impl.ui.RescuerDetailsParentFragment
import dagger.Binds
import dagger.Module

@Module(
    subcomponents = [RescuerDetailsComponent::class]
)
interface RescuerDetailsFeatureModule {

    @RescuerDetailsFeatureScope
    @Binds
    fun bindRescuerDetailsFragmentImpl(impl: RescuerDetailsParentFragment): BaseRescuerDetailsFragment

}
