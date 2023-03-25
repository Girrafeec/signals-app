package com.girrafeecstud.signals.rescuer_details_impl.di

import com.girrafeecstud.signals.rescuer_details_api.ui.RescuerDetailsFragment
import com.girrafeecstud.signals.rescuer_details_impl.di.annotation.RescuerDetailsFeatureScope
import com.girrafeecstud.signals.rescuer_details_impl.ui.RescuerDetailsFragmentImpl
import dagger.Binds
import dagger.Module

@Module
interface RescuerDetailsFeatureModule {

    @RescuerDetailsFeatureScope
    @Binds
    fun bindRescuerDetailsFragmentImpl(impl: RescuerDetailsFragmentImpl): RescuerDetailsFragment

}
