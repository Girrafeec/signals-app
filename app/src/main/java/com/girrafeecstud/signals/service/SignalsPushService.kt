package com.girrafeecstud.signals.service

import android.content.Intent
import android.util.Log
import com.girrafeecstud.signals.rescuers_api.utils.RescuersFeatureUtils
import com.girrafeecstud.signals.rescuers_impl.engine.RescuersFeatureReceiver
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
            val intent = prepareBroadcastIntent(action = message.data.get("action"))
            sendBroadcast(intent)
        }

    }

    private fun prepareBroadcastIntent(action: String?): Intent =
        when (action) {
            RescuersFeatureUtils.ACTION_START_RESCUERS_ENGINE -> {
                Intent(
                    applicationContext,
                    RescuersFeatureReceiver::class.java
                ).apply {
                    this.action = action
                }
            }
            else -> {
                Intent()
            }
        }
}