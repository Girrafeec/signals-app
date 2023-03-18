package com.girrafeecstud.signals.feature_auth.di.provider

import com.girrafeecstud.signals.feature_auth.di.AuthComponent

interface AuthComponentProvider {

    fun getAuthComponent(): AuthComponent

}