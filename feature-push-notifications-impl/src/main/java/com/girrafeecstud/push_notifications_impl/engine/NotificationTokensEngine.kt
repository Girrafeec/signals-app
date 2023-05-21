/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_impl.engine

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import com.girrafeecstud.push_notifications_api.engine.INotificationTokensEngine
import com.girrafeecstud.push_notifications_impl.service.NotificationTokensService
import javax.inject.Inject

class NotificationTokensEngine @Inject constructor(
    private val context: Context
) : INotificationTokensEngine {

    override fun startEngine() {
        if (isNotificationTokensServiceRunning())
            return
        val intent = Intent(context, NotificationTokensService::class.java)
        context.startService(intent)
    }

    private fun isNotificationTokensServiceRunning(): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == NotificationTokensService::class.java.name
        }
    }
}