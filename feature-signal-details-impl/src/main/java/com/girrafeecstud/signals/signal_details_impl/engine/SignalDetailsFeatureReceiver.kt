package com.girrafeecstud.signals.signal_details_impl.engine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.girrafeecstud.signals.signal_details_impl.di.SignalDetailsFeatureComponent
import com.girrafeecstud.signals.signal_details_impl.di.SignalDetailsFeatureReceiverComponent
import com.girrafeecstud.signals.signal_details_impl.ui.SignalDetailsFragment
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import com.girrafeecstud.signals.signals_api.utils.SignalsFeatureUtils
import javax.inject.Inject

class SignalDetailsFeatureReceiver : BroadcastReceiver() {

    private var _signalDetailsFeatureReceiverComponent: SignalDetailsFeatureReceiverComponent? = null

    private val signalDetailsFeatureReceiverComponent get() = _signalDetailsFeatureReceiverComponent!!

    @Inject
    lateinit var signalsEngine: SignalsEngine

    init {
        if (_signalDetailsFeatureReceiverComponent == null) {
            _signalDetailsFeatureReceiverComponent = SignalDetailsFeatureComponent.signalDetailsFeatureComponent.receiverComponent().build()
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        signalDetailsFeatureReceiverComponent.injectReceiver(receiver = this)
        if (!intent?.action.equals(SignalsFeatureUtils.ACTION_START_SIGNALS_ENGINE))
            return
        SignalDetailsFragment.signalId?.let { signalId ->
            if (SignalDetailsFragment.isShown)
                signalsEngine.updateSignalDetails(context = context!!, signalId = signalId)
        }
        _signalDetailsFeatureReceiverComponent = null
    }
}