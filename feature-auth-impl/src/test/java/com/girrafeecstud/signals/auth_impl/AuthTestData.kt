/* Created by Girrafeec */

package com.girrafeecstud.signals.auth_impl

import com.girrafeecstud.signals.auth_impl.login.domain.UserLoginEntity

object AuthTestData {

    const val BEARER_TOKEN = "Bearer rgerg45g5g5g5g43ggq_4f4fg4g"

    val loginEntity = UserLoginEntity(
        userPhoneNumber = "+79434543454",
        userPassword = "234r43f23f4f432fdsf"
    )

}