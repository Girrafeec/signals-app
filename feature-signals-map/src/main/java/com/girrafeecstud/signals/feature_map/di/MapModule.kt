package com.girrafeecstud.signals.feature_map.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.feature_map.di.annotation.MapScope
import com.girrafeecstud.signals.feature_map.presentation.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MapModule {
    @Binds
    @IntoMap
    @MapScope
    @ViewModelKey(MapViewModel::class)
    fun bindMapViewModel(impl: MapViewModel): ViewModel
}