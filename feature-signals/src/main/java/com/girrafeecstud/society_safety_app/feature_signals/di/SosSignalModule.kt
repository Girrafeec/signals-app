package com.girrafeecstud.society_safety_app.feature_signals.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.feature_signals.di.annotation.SosSignalScope
import com.girrafeecstud.society_safety_app.feature_signals.presentation.SosSignalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SosSignalModule {

    @Binds
    @SosSignalScope
    @ViewModelKey(SosSignalViewModel::class)
    @IntoMap
    fun bindSosSignalViewModel(impl: SosSignalViewModel): ViewModel

}