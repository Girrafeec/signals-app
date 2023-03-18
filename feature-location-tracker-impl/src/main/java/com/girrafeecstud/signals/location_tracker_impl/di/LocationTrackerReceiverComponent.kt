package com.girrafeecstud.signals.location_tracker_impl.di

import com.girrafeecstud.signals.location_tracker_impl.di.annotation.LocationTrackerReceiverScope
import com.girrafeecstud.signals.location_tracker_impl.engine.LocationTrackerReceiver
import dagger.Subcomponent

@LocationTrackerReceiverScope
@Subcomponent
interface LocationTrackerReceiverComponent {

    fun injectReceiver(receiver: LocationTrackerReceiver)

    @Subcomponent.Builder
    interface Builder {

        fun build(): LocationTrackerReceiverComponent

    }

}