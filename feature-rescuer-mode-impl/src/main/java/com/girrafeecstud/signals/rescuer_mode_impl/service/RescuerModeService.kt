/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuer_mode_api.engine.RescuerModeState
import com.girrafeecstud.signals.rescuer_mode_impl.di.RescuerModeFeatureComponent
import com.girrafeecstud.signals.rescuer_mode_impl.domain.AcceptSosSignalUseCase
import com.girrafeecstud.signals.rescuer_mode_impl.domain.RejectSosSignalUseCase
import com.girrafeecstud.signals.rescuer_mode_impl.utils.RescuerModeFeatureUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RescuerModeService : Service() {

    companion object {

        private var _state: MutableStateFlow<RescuerModeState> =
            MutableStateFlow(RescuerModeState())
        val state = _state.asStateFlow()

    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val binder = RescuerModeServiceBinder()

    @Inject
    lateinit var acceptSosSignalUseCase: AcceptSosSignalUseCase

    @Inject
    lateinit var rejectSosSignalUseCase: RejectSosSignalUseCase

    override fun onCreate() {
        super.onCreate()
        RescuerModeFeatureComponent.rescuerModeFeatureComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i("tag", "on bind")
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                RescuerModeFeatureUtils.ACTION_ACCEPT_SOS_SIGNAL -> {
                    startForeground(999, getNotification())
                    val signalId = it.getStringExtra("signalId")
                    signalId?.let {
                        _state.update { it.copy(signalId = signalId) }
                        acceptSosSignal()
                    }
                }
                RescuerModeFeatureUtils.ACTION_REJECT_SOS_SIGNAL -> {
                    rejectSosSignal()
                }
                //TODO refactor
                else -> {}
            }
        }

        return START_STICKY
    }

    private fun getNotification(
        title: String = "Режим спасателя",
        text: String = ""
    ): Notification {

        val sosSignalNotification: Notification.Builder?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = "Foreground Rescuer Mode"
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
            sosSignalNotification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(com.girrafeecstud.core_ui.R.drawable.ic_bell)
        }
        else {
            @Suppress("DEPRECATION")
            sosSignalNotification = Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(com.girrafeecstud.core_ui.R.drawable.ic_bell)
        }

        return sosSignalNotification.build()
    }

    private fun acceptSosSignal() {
        serviceScope.launch {
            acceptSosSignalUseCase(signalId = state.value.signalId!!)
                .collect { result ->
                    when (result) {
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {}
                        is BusinessResult.Success -> {
                            _state.update { it.copy(signalAccepted = true, signalRejected = null) }
                        }
                    }
                }
        }
    }

    private fun rejectSosSignal() {
        serviceScope.launch {
            rejectSosSignalUseCase(signalId = state.value.signalId!!)
                .collect { result ->
                    when (result) {
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {}
                        is BusinessResult.Success -> {
                            _state.update { it.copy(signalAccepted = null, signalRejected = true) }
                            stopSelf()
                        }
                    }
                }
        }
    }

    inner class RescuerModeServiceBinder : Binder() {
        fun getService() = this@RescuerModeService
    }

}