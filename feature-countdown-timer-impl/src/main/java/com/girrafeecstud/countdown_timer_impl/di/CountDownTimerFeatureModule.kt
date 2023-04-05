package com.girrafeecstud.countdown_timer_impl.di

import com.girrafeecstud.countdown_timer_api.data.BaseCountDownTimerManager
import com.girrafeecstud.countdown_timer_api.engine.BaseCountDownTimerEngine
import com.girrafeecstud.countdown_timer_impl.data.CountDownTimerManager
import com.girrafeecstud.countdown_timer_impl.engine.CountDownTimerEngine
import dagger.Binds
import dagger.Module

@Module(includes = [CountDownTimerFeatureModule.CountDownTimerFeatureBindModule::class])
class CountDownTimerFeatureModule {

    @Module
    interface CountDownTimerFeatureBindModule {

        @CountDownTimerFeatureScope
        @Binds
        fun bindCountDownTimerManager(impl: CountDownTimerManager): BaseCountDownTimerManager

        @CountDownTimerFeatureScope
        @Binds
        fun bindCountDownTimerEngine(impl: CountDownTimerEngine): BaseCountDownTimerEngine

    }

}
