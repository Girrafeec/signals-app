/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.feature_map.presentation.RescuerModeMapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RescuerModeMapModule {

    @Binds
    @RescuerModeMapScope
    @IntoMap
    @ViewModelKey(RescuerModeMapViewModel::class)
    fun bindRescuerModeMapViewModel(impl: RescuerModeMapViewModel): ViewModel

}