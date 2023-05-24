package com.girrafeecstud.signals.signals_impl.engine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import com.girrafeecstud.signals.signals_api.utils.SignalsFeatureUtils
import com.girrafeecstud.signals.signals_impl.di.SignalsFeatureComponent
import com.girrafeecstud.signals.signals_impl.di.SignalsFeatureReceiverComponent
import javax.inject.Inject

class SignalsFeatureReceiver : BroadcastReceiver() {

    private var _signalsFeatureReceiverComponent: SignalsFeatureReceiverComponent? = null

    private val signalsFeatureReceiverComponent get() = _signalsFeatureReceiverComponent!!

    init {
        if (_signalsFeatureReceiverComponent == null) {
            _signalsFeatureReceiverComponent = SignalsFeatureComponent.signalsFeatureComponent.receiverComponent().build()
        }
    }

    @Inject
    lateinit var signalsEngine: SignalsEngine

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("tag sos signals", "receiver")
        signalsFeatureReceiverComponent.injectReceiver(receiver = this)
        if (intent?.action.equals(SignalsFeatureUtils.ACTION_START_SIGNALS_ENGINE)) {
            signalsEngine.startSignalsEngine() //TODO null safety problem
        }
        _signalsFeatureReceiverComponent = null
    }
}