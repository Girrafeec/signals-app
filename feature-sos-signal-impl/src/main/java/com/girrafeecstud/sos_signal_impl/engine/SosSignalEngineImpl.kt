package com.girrafeecstud.sos_signal_impl.engine

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import com.girrafeecstud.sos_signal_impl.service.SosSignalService
import com.girrafeecstud.sos_signal_impl.utils.SosSignalUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class SosSignalEngineImpl @Inject constructor(
) : SosSignalEngine {

    override fun enableSosSignal(context: Context, sosSignal: SosSignal) {
        Log.i("tag sos", "enable")
        // TODO?
//        if (isSosSignalServiceRunning(context = context))
//            return
        sendCommandToService(context = context, sosSignal = sosSignal, action = SosSignalUtils.ACTION_SEND_SOS_SIGNAL)
    }

    override fun updateSosSignal(context: Context, sosSignal: SosSignal) =
        sendCommandToService(context = context, sosSignal = sosSignal, action = SosSignalUtils.ACTION_UPDATE_SOS_SIGNAL)

    override fun disableSosSignal(context: Context) =
        sendCommandToService(context = context, action = SosSignalUtils.ACTION_DISABLE_SOS_SIGNAL)

    override fun getSosSignalState(): Flow<SosSignalState> =
        SosSignalService.sosSignalState

    private fun isSosSignalServiceRunning(context: Context): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == SosSignalService::class.java.name
        }
    }

    private fun sendCommandToService(
        context: Context,
        sosSignal: SosSignal? = null,
        action: String
    ) {
        Log.i("tag sos", "send command")
        val intent = Intent(context, SosSignalService::class.java).apply {
            this.action = action
            this.putExtra("sosSignal", sosSignal)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
            return
        }
        context.startService(intent)
    }
}