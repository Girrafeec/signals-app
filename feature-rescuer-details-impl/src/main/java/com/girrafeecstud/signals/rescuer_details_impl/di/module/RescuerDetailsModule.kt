package com.girrafeecstud.signals.rescuer_details_impl.di.module

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.rescuer_details_impl.di.annotation.RescuerDetailsScope
import com.girrafeecstud.signals.rescuer_details_impl.presentation.RescuerDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RescuerDetailsModule {

    @Binds
    @RescuerDetailsScope
    @IntoMap
    @ViewModelKey(RescuerDetailsViewModel::class)
    fun bindRescuerDetailsViewModel(impl: RescuerDetailsViewModel): ViewModel

}
