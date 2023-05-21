/* Created by Girrafeec */

package com.girrafeecstud.signals.auth_api.di

import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.auth_api.data.IAuthRepository

interface AuthFeatureApi {

    fun getAuthDataSource(): IAuthDataSource

    fun getAuthRepository(): IAuthRepository

}