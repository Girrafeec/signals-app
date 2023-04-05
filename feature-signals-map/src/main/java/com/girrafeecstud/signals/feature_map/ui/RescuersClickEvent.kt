/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.ui

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer


interface RescuersClickEvent {

    fun onRescuerClick(rescuer: Rescuer?)

}