package com.girrafeecstud.signals.signal_details_impl.di

import com.girrafeecstud.signals.signal_details_impl.di.annotation.SignalDetailsFeatureReceiverScope
import com.girrafeecstud.signals.signal_details_impl.engine.SignalDetailsFeatureReceiver
import dagger.Subcomponent

@SignalDetailsFeatureReceiverScope
@Subcomponent
interface SignalDetailsFeatureReceiverComponent {

    fun injectReceiver(receiver: SignalDetailsFeatureReceiver)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignalDetailsFeatureReceiverComponent

    }

}