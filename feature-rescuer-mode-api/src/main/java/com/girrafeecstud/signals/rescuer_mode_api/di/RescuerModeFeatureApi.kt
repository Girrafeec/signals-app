/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_api.di

import com.girrafeecstud.signals.rescuer_mode_api.engine.IRescuerModeEngine

interface RescuerModeFeatureApi {

    fun getRescuerModeEngine(): IRescuerModeEngine

}