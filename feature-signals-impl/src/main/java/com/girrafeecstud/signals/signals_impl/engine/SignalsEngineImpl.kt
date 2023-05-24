package com.girrafeecstud.signals.signals_impl.engine

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import com.girrafeecstud.signals.signals_api.domain.entity.SignalSender
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import com.girrafeecstud.signals.signals_api.engine.SignalsEngineState
import com.girrafeecstud.signals.signals_impl.service.SignalsService
import com.girrafeecstud.signals.signals_impl.utils.SignalsFeatureImplUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignalsEngineImpl @Inject constructor(
    private val context: Context
) : SignalsEngine {

    override fun startSignalsEngine() =
        sendCommandToService(
            context = context,
            action = SignalsFeatureImplUtils.ACTION_GET_SIGNALS
        )

    override fun getSignalDetails(signalId: String) =
        sendCommandToService(
            context = context,
            signalId = signalId,
            action = SignalsFeatureImplUtils.ACTION_GET_SIGNAL_DETAILS
        )

    override fun getState(): Flow<SignalsEngineState> =
        SignalsService.signalsEngineState

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