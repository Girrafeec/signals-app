package com.girrafeecstud.society_safety_app.feature_map.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_map.di.annotation.SignalsMapScope
import com.girrafeecstud.society_safety_app.feature_map.presentation.SignalsMapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignalsMapModule {

    @Binds
    @IntoMap
    @SignalsMapScope
    @ViewModelKey(SignalsMapViewModel::class)
    abstract fun bindSignalsMapViewModel(impl: SignalsMapViewModel): ViewModel

}