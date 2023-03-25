package com.girrafeecstud.signals.signal_details_impl.di

import com.girrafeecstud.signals.signal_details_impl.di.annotation.SignalDetailsScope
import com.girrafeecstud.signals.signal_details_impl.ui.SignalDetailsFragment
import dagger.Subcomponent

@SignalDetailsScope
@Subcomponent(
    modules = [SignalDetailsModule::class]
)
interface SignalDetailsComponent {

    fun inject(fragment: SignalDetailsFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignalDetailsComponent

    }

}