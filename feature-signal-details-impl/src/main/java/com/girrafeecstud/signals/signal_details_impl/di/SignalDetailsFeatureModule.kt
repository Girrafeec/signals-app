package com.girrafeecstud.signals.signal_details_impl.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.signal_details_impl.di.annotation.SignalDetailsFeatureScope
import com.girrafeecstud.signals.signal_details_impl.ui.SignalDetailsFragment
import com.girrafeecstud.signals.signal_details_api.ui.BaseSignalDetailsFragment
import com.girrafeecstud.signals.signal_details_impl.presentation.SignalDetailsViewModel
import com.girrafeecstud.signals.signal_details_impl.ui.SignalDetailsParentFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        SignalDetailsComponent::class,
        SignalDetailsFeatureReceiverComponent::class
    ]
)
interface SignalDetailsFeatureModule {

    @SignalDetailsFeatureScope
    @Binds
    fun bindSignalDetailsFragment(impl: SignalDetailsParentFragment): BaseSignalDetailsFragment

//    @SignalDetailsFeatureScope
//    @Binds
//    @IntoMap
//    @ViewModelKey(SignalDetailsViewModel::class)
//    fun bindSignalDetailsViewModel(impl: SignalDetailsViewModel): ViewModel

}
