package com.girrafeecstud.society_safety_app.core_network.data.di

import com.girrafeecstud.society_safety_app.core_network.data.network.NetworkConfig

interface CoreNetworkApi {

    fun networkConfig(): NetworkConfig

}