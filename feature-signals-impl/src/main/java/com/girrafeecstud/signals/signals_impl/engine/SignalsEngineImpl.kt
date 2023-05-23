package com.girrafeecstud.signals.signals_impl.engine

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import com.girrafeecstud.signals.signals_impl.service.SignalsService
import com.girrafeecstud.signals.signals_impl.utils.SignalsFeatureImplUtils
import javax.inject.Inject

class SignalsEngineImpl @Inject constructor(

) : SignalsEngine {

    override fun startSignalsEngine(context: Context) =
        sendCommandToService(
            context = context,
            action = SignalsFeatureImplUtils.ACTION_GET_SIGNALS
        )

    override fun updateSignalDetails(context: Context, signalId: String) =
        sendCommandToService(
            context = context,
            signalId = signalId,
            action = SignalsFeatureImplUtils.ACTION_GET_SIGNAL_DETAILS
        )

    private fun isRescuersServiceRunning(context: Context): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == SignalsService::class.java.name
        }
    }

    private fun sendCommandToService(
        context: Context,
        signalId: String? = null,
        action: String
    ) {
        val intent = Intent(context, SignalsService::class.java).apply {
            this.action = action
            this.putExtra("signalId", signalId)
        }
        context.startService(intent)
    }

}