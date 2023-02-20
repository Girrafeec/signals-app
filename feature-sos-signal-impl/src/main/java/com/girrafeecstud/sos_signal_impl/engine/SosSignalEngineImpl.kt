package com.girrafeecstud.sos_signal_impl.engine

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_impl.service.SosSignalService
import javax.inject.Inject

class SosSignalEngineImpl @Inject constructor(

) : SosSignalEngine {

    private var _sosSignal: SosSignal? = null

    private val sosSignal get() = _sosSignal!!

    private var _sosSignalService: SosSignalService? = null

    private val sosSignalService get() = _sosSignalService!!

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
            val binder: SosSignalService.SosSignalServiceBinder = iBinder as SosSignalService.SosSignalServiceBinder
            _sosSignalService = binder.getService()
            sosSignalService.startSosSignal(sosSignal = sosSignal)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            _sosSignalService = null
        }
    }

    override fun startSosSignal(context: Context, sosSignal: SosSignal) {
        if (isSosSignalServiceRunning(context = context))
            return
        this._sosSignal = sosSignal
        val sosSignalServiceIntent = Intent(context, SosSignalService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.bindService(sosSignalServiceIntent, serviceConnection, BIND_AUTO_CREATE)
            context.startForegroundService(sosSignalServiceIntent)
            return
        }
        context.bindService(sosSignalServiceIntent, serviceConnection, BIND_AUTO_CREATE)
        context.startService(sosSignalServiceIntent)

    }

    override fun disableSosSignal(context: Context) {
        sosSignalService.stopSosSignal()
        context.unbindService(serviceConnection)
        _sosSignalService = null
    }

    private fun isSosSignalServiceRunning(context: Context): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == SosSignalService::class.java.name
        }
    }
}