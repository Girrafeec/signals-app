package com.girrafeecstud.sos_signal_impl.di.dependencies

import android.content.Context
import com.girrafeecstud.countdown_timer_api.engine.BaseCountDownTimerEngine
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.event_bus.EventBus
import retrofit2.Retrofit

interface SosSignalDependencies {

    fun getEventBus(): EventBus

    fun getCountDownTimerEngine(): BaseCountDownTimerEngine

    fun getRetrofit(): Retrofit

    fun getAuthDataSource(): IAuthDataSource

    fun getContext(): Context
}