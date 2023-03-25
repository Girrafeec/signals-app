package com.girrafeecstud.signals.feature_map.ui

import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal

interface SignalsClickEvent {

    fun onSignalClick(signal: EmergencySignal?)

}