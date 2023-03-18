package com.girrafeecstud.signals.feature_map.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.feature_map.di.annotation.SosMapScope
import com.girrafeecstud.signals.feature_map.presentation.SosMapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SosMapModule {

    @Binds
    @IntoMap
    @SosMapScope
    @ViewModelKey(SosMapViewModel::class)
    fun bindSosSignalMapViewModel(impl: SosMapViewModel): ViewModel

}
