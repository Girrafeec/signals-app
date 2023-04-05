/* Created by Girrafeec */

package com.girrafeecstud.countdown_timer_api.di

import com.girrafeecstud.countdown_timer_api.data.BaseCountDownTimerManager
import com.girrafeecstud.countdown_timer_api.engine.BaseCountDownTimerEngine

interface CountDownTimerFeatureApi {

    fun getICountDownTimerManager(): BaseCountDownTimerManager

    fun getCountDownTimerEngine(): BaseCountDownTimerEngine

}