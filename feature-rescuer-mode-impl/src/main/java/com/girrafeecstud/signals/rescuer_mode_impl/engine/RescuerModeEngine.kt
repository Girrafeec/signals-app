/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.engine

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.girrafeecstud.signals.rescuer_mode_api.engine.IRescuerModeEngine
import com.girrafeecstud.signals.rescuer_mode_api.engine.RescuerModeState
import com.girrafeecstud.signals.rescuer_mode_impl.service.RescuerModeService
import com.girrafeecstud.signals.rescuer_mode_impl.utils.RescuerModeFeatureUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RescuerModeEngine @Inject constructor(
    private val context: Context
) : IRescuerModeEngine {

    override fun acceptSosSignal(signalId: String) =
        sendCommandToService(
            context = context,
            signalId = signalId,
            action = RescuerModeFeatureUtils.ACTION_ACCEPT_SOS_SIGNAL
        )

    override fun rejectSosSignal() =
        sendCommandToService(
            context = context,
            action = RescuerModeFeatureUtils.ACTION_REJECT_SOS_SIGNAL
        )


    override fun getRescuerModeState(): Flow<RescuerModeState> =
        RescuerModeService.state

    private fun sendCommandToService(
        context: Context,
        signalId: String? = null,
        action: String
    ) {
        Log.i("tag rescuer mode", "send command")
        val intent = Intent(context, RescuerModeService::class.java).apply {
            this.action = action
            this.putExtra("signalId", signalId)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
            return
        }
        context.startService(intent)
    }
}