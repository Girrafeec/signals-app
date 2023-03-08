package com.girrafeecstud.society_safety_app.feature_map.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_map.di.annotation.SosMapScope
import com.girrafeecstud.society_safety_app.feature_map.presentation.SosSignalMapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SosSignalMapModule {

    @Binds
    @IntoMap
    @SosMapScope
    @ViewModelKey(SosSignalMapViewModel::class)
    abstract fun bindSosSignalMapViewModel(impl: SosSignalMapViewModel): ViewModel

}
