package com.girrafeecstud.location_tracker_api.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object TrackerPermissionsUtility {

    private const val LOCATION_PERMISSIONS_REQUEST_CODE = 228

    fun locationPermissionsGranted(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )) == PackageManager.PERMISSION_GRANTED && (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )) == PackageManager.PERMISSION_GRANTED
        }
        return (ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )) == PackageManager.PERMISSION_GRANTED && (ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )) == PackageManager.PERMISSION_GRANTED && (ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermissions(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSIONS_REQUEST_CODE
            )
            return
        }
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
            LOCATION_PERMISSIONS_REQUEST_CODE
        )
        ActivityCompat.requestPermissions(
            context,
            arrayOf(
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ),
            LOCATION_PERMISSIONS_REQUEST_CODE
        )
    }

}