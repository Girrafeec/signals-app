/* Created by Girrafeec */

package com.girrafeecstud.on_board.ui

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

sealed class AppPermission (
    val permissions: Array<String>,
    val permissionRequestId: Int
    ) {

    object Location : AppPermission(
        permissions =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        else
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
        permissionRequestId = 1
    )

    object PhoneCall : AppPermission(
        permissions = arrayOf(
            Manifest.permission.CALL_PHONE
        ),
        permissionRequestId = 2
    )

    object Contacts : AppPermission(
        permissions = arrayOf(
            Manifest.permission.READ_CONTACTS
        ),
        permissionRequestId = 3
    )

    object Notifications : AppPermission(
        permissions = arrayOf(
            Manifest.permission.POST_NOTIFICATIONS
        ),
        permissionRequestId = 4
    )

}