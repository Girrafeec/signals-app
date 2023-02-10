package com.girrafeecstud.sos_signal_impl.engine

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_impl.service.SosSignalService
import javax.inject.Inject

class SosSignalEngineImpl @Inject constructor(

) : SosSignalEngine {

    override fun startSosSignal(context: Context) {
        if (isSosSignalServiceRunning(context = context))
            return
        val sosSignalServiceIntent = Intent(context, SosSignalService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(sosSignalServiceIntent)
            return
        }
        context.startService(sosSignalServiceIntent)
    }

    override fun disableSosSignal() {
        TODO("Not yet implemented")
    }

    private fun isSosSignalServiceRunning(context: Context): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == SosSignalService::class.java.name
        }
    }
}