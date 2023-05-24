package com.girrafeecstud.signals.rescuers_impl.engine

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngineState
import com.girrafeecstud.signals.rescuers_impl.service.RescuersService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RescuersEngineImpl @Inject constructor(

) : RescuersEngine {

    override fun startRescuersEngine(context: Context) {
        if (isRescuersServiceRunning(context))
            return
        val intent = Intent(context, RescuersService::class.java)
        context.startService(intent)
    }

    override fun getState(): Flow<RescuersEngineState> =
        RescuersService.rescuersEngineState

    private fun isRescuersServiceRunning(context: Context): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == RescuersService::class.java.name
        }
    }

}