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
import com.girrafeecstud.sos_signal_impl.R
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

    private var _sosSignalNotification: Notification.Builder? = null

    private val sosSignalNotification get() = _sosSignalNotification!!

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

        setupNotification()

        startForeground(999, sosSignalNotification.build())

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder {
        Log.i("tag", "on bind")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("tag", "on unbind")
        return super.onUnbind(intent)
    }

    private fun setupNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = "Foreground Sos Signal"
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_HIGH
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
            _sosSignalNotification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Sos signal sending")
        }
        else {
            _sosSignalNotification = Notification.Builder(this)
                .setContentTitle("Sos signal sending")
        }
    }

    private fun sendSosSignal(sosSignal: SosSignal) {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        scope.launch {
            sendSosSignalUseCase(sosSignal = sosSignal)
                .collect {
                    Log.i("tag", "sos signal result collected")
                }
        }
    }

    fun startSosSignal(sosSignal: SosSignal) {
        sendSosSignal(sosSignal = sosSignal)
    }

    fun stopSosSignal() {

    }

    inner class SosSignalServiceBinder : Binder() {
        fun getService() = this@SosSignalService
    }
}