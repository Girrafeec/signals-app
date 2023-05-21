/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.push_notifications_api.data.INotificationTokensRepository
import com.girrafeecstud.push_notifications_impl.di.PushNotificationsFeatureComponent
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NotificationTokensService : Service() {

    private val binder = NotificationTokensServiceBinder()

    private val serviceMainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var notificationTokensRepository: INotificationTokensRepository

    override fun onCreate() {
        super.onCreate()
        PushNotificationsFeatureComponent.pushNotificationsFeatureComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendNotificationToken()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private fun sendNotificationToken() {
        serviceScope.launch {
            val isNotificationTokenSent =
                serviceScope.async { notificationTokensRepository.isNotificationTokenSent() }
            if (!isNotificationTokenSent.await()) {
                notificationTokensRepository.sendNotificationToken()
                .onEach { result ->
                    when (result) {
                        is BusinessResult.Success -> {
                            Log.i("notifications service", "successfully sent")
                        }
                        is BusinessResult.Error -> {
                            Log.i("notifications service", "error")
                        }
                        is BusinessResult.Exception -> {
                            Log.i("notifications service", "exception")
                        }
                    }
                    stopSelf()
                }
                .launchIn(serviceScope)

            }
        }
    }

    inner class NotificationTokensServiceBinder : Binder() {
        fun getService() = this@NotificationTokensService
    }

}