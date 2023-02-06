package com.girrafeecstud.society_safety_app.location_tracker_impl.di

import com.girrafeecstud.society_safety_app.location_tracker_impl.di.annotation.LocationTrackerReceiverScope
import com.girrafeecstud.society_safety_app.location_tracker_impl.engine.LocationTrackerReceiver
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