package com.girrafeecstud.signals.signal_details_impl.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.signal_details_impl.di.annotation.SignalDetailsScope
import com.girrafeecstud.signals.signal_details_impl.presentation.SignalDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignalDetailsModule {

//    @SignalDetailsScope
//    @Binds
//    @IntoMap
//    @ViewModelKey(SignalDetailsViewModel::class)
//    fun bindSignalDetailsViewModel(impl: SignalDetailsViewModel): ViewModel

}
