package com.girrafeecstud.sos_signal_impl.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignalType
import com.girrafeecstud.sos_signal_impl.di.SosSignalFeatureComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosSignalService : Service() {

    private val binder = SosSignalServiceBinder()

   @Inject
    lateinit var sendSosSignalUseCase: SendSosSignalUseCase

    override fun onCreate() {
        SosSignalFeatureComponent.sosSignalFeatureComponent.inject(this)
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notification: Notification.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = "Foreground Sos Signal"
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_HIGH
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
            notification = Notification.Builder(this, CHANNEL_ID)
                .setContentText("sos signal description")
                .setContentTitle("Sos signal sending")
        }
        else {
            notification = Notification.Builder(this)
                .setContentText("sos signal description")
                .setContentTitle("Sos signal sending")
        }

        startSosSignal()
        startForeground(999, notification.build())

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    private fun startSosSignal() {
        val sosSignal = SosSignal(
            signalTitle = "Default title",
            signalDescription = "Default description",
            signalType = SosSignalType.DEFAULT_SOS_SIGNAL
        )
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        scope.launch {
            sendSosSignalUseCase(sosSignal = sosSignal)
                .collect {
                    Log.i("tag", "sos signal result collected")
                }
        }
    }

    inner class SosSignalServiceBinder : Binder() {
        fun getService() = this@SosSignalService
    }
}