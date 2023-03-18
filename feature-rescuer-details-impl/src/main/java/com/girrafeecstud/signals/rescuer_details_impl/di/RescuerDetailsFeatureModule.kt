package com.girrafeecstud.signals.rescuer_details_impl.di

import com.girrafeecstud.signals.rescuer_details_api.ui.RescuerDetailsFragmentDialog
import com.girrafeecstud.signals.rescuer_details_impl.di.annotation.RescuerDetailsFeatureScope
import com.girrafeecstud.signals.rescuer_details_impl.ui.RescuerDetailsFragmentDialogImpl
import dagger.Binds
import dagger.Module

@Module
interface RescuerDetailsFeatureModule {

    @RescuerDetailsFeatureScope
    @Binds
    fun bindRescuerDetailsFragmentDialogImpl(impl: RescuerDetailsFragmentDialogImpl): RescuerDetailsFragmentDialog

}
