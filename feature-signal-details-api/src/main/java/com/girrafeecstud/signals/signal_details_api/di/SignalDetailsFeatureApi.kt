package com.girrafeecstud.signals.signal_details_api.di

import com.girrafeecstud.signals.signal_details_api.ui.BaseSignalDetailsFragment

interface SignalDetailsFeatureApi {

    fun getSignalDetailsFragment(): BaseSignalDetailsFragment

}