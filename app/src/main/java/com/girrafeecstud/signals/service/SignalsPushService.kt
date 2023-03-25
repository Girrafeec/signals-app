package com.girrafeecstud.signals.service

import android.content.Intent
import android.util.Log
import com.girrafeecstud.signals.rescuers_api.utils.RescuersFeatureUtils
import com.girrafeecstud.signals.rescuers_impl.engine.RescuersFeatureReceiver
import com.girrafeecstud.signals.signal_details_impl.engine.SignalDetailsFeatureReceiver
import com.girrafeecstud.signals.signals_api.utils.SignalsFeatureUtils
import com.girrafeecstud.signals.signals_impl.engine.SignalsFeatureReceiver
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class SignalsPushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.i("tag", "new token $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.i("tag", "new message ${message.data}")

        if (message.notification != null) {
            Log.i("tag", "new message ${message.notification!!.clickAction.toString()}")
            Log.i("tag", "new message has notification")
        }

        if (message.data != null) {

            Log.i("tag", "new message action ${message.data.get("action")}")
            val intents = prepareBroadcastIntents(action = message.data.get("action"))
            for (intent in intents) {
                if (intent.action == null)
                    continue
                else
                    sendBroadcast(intent)
            }
        }

    }

    private fun prepareBroadcastIntents(
        action: String?
    ): List<Intent> =
        when (action) {
            RescuersFeatureUtils.ACTION_START_RESCUERS_ENGINE -> {
                listOf(
                    Intent(
                        applicationContext,
                        RescuersFeatureReceiver::class.java
                    ).apply {
                        this.action = action
                    }
                )
            }
            SignalsFeatureUtils.ACTION_START_SIGNALS_ENGINE -> {
                listOf(
                    Intent(
                        applicationContext,
                        SignalsFeatureReceiver::class.java
                    ).apply {
                        this.action = action
                    },
                    Intent(
                        applicationContext,
                        SignalDetailsFeatureReceiver::class.java
                    ).apply {
                        this.action = action
                    }
                )
            }
            else -> {
                listOf(Intent())
            }
        }
}