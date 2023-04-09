/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_details_impl.di.component

import com.girrafeecstud.signals.rescuer_details_impl.di.annotation.RescuerDetailsScope
import com.girrafeecstud.signals.rescuer_details_impl.di.module.RescuerDetailsModule
import com.girrafeecstud.signals.rescuer_details_impl.ui.RescuerDetailsFragment
import dagger.Subcomponent

@RescuerDetailsScope
@Subcomponent(
    modules = [RescuerDetailsModule::class]
)
interface RescuerDetailsComponent {

    fun inject(fragment: RescuerDetailsFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): RescuerDetailsComponent

    }

}