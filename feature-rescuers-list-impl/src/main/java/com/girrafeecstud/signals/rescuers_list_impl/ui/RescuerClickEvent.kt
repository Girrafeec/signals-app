package com.girrafeecstud.signals.rescuers_list_impl.ui

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer

interface RescuerClickEvent {

    fun onRescuerClick(rescuer: Rescuer)

}