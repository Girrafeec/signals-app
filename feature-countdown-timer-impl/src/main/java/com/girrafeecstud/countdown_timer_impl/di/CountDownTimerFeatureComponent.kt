/* Created by Girrafeec */

package com.girrafeecstud.countdown_timer_impl.di

import com.girrafeecstud.countdown_timer_api.di.CountDownTimerFeatureApi
import dagger.Component
import dagger.Component.Builder

@CountDownTimerFeatureScope
@Component(modules = [CountDownTimerFeatureModule::class])
interface CountDownTimerFeatureComponent : CountDownTimerFeatureApi {

    @Component.Builder
    interface Builder {
        fun build(): CountDownTimerFeatureComponent
    }

    companion object {

        private var _countDownTimerFeatureComponent: CountDownTimerFeatureComponent? = null

        val countDownTimerFeatureComponent get() = _countDownTimerFeatureComponent!!

        fun init() {
            if (_countDownTimerFeatureComponent == null)
                _countDownTimerFeatureComponent = DaggerCountDownTimerFeatureComponent
                    .builder()
                    .build()
        }

        fun reset() {
            _countDownTimerFeatureComponent = null
        }

    }

}