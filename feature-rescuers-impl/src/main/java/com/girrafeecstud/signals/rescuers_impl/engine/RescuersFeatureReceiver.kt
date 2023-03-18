package com.girrafeecstud.signals.rescuers_impl.engine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine
import com.girrafeecstud.signals.rescuers_api.utils.RescuersFeatureUtils
import com.girrafeecstud.signals.rescuers_impl.di.RescuersFeatureComponent
import com.girrafeecstud.signals.rescuers_impl.di.RescuersFeatureReceiverComponent
import javax.inject.Inject

class RescuersFeatureReceiver : BroadcastReceiver() {

    private var _rescuersFeatureReceiverComponent: RescuersFeatureReceiverComponent? = null

    private val rescuersFeatureReceiverComponent get() = _rescuersFeatureReceiverComponent!!

    init {
        if (_rescuersFeatureReceiverComponent == null) {
            _rescuersFeatureReceiverComponent = RescuersFeatureComponent.rescuersFeatureComponent.receiverComponent().build()
        }
    }

    @Inject
    lateinit var rescuersEngine: RescuersEngine

    override fun onReceive(context: Context?, intent: Intent?) {
        rescuersFeatureReceiverComponent.injectReceiver(receiver = this)
        if (intent?.action.equals(RescuersFeatureUtils.ACTION_START_RESCUERS_ENGINE)) {
            rescuersEngine.startRescuersEngine(context = context!!) //TODO null safety problem
        }
        _rescuersFeatureReceiverComponent = null
    }
}