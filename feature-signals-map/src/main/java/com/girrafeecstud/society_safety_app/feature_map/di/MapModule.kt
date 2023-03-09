package com.girrafeecstud.society_safety_app.feature_map.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_map.di.annotation.MapScope
import com.girrafeecstud.society_safety_app.feature_map.di.annotation.MapsFeatureScope
import com.girrafeecstud.society_safety_app.feature_map.presentation.MapViewModel
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