package com.girrafeecstud.signals.rescuers_impl.di

import com.girrafeecstud.signals.rescuers_impl.di.annotation.RescuersFeatureReceiverScope
import com.girrafeecstud.signals.rescuers_impl.engine.RescuersFeatureReceiver
import dagger.Component.Builder
import dagger.Subcomponent

@RescuersFeatureReceiverScope
@Subcomponent
interface RescuersFeatureReceiverComponent {

    fun injectReceiver(receiver: RescuersFeatureReceiver)

    @Subcomponent.Builder
    interface Builder {

        fun build(): RescuersFeatureReceiverComponent

    }

}