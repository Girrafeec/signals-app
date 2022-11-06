package com.girrafeecstud.society_safety_app.feature_auth.di.provider

import com.girrafeecstud.society_safety_app.feature_auth.di.AuthComponent

interface AuthComponentProvider {

    fun getAuthComponent(): AuthComponent

}