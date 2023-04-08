package com.girrafeecstud.sos_signal_impl.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.countdown_timer_api.engine.BaseCountDownTimerEngine
import com.girrafeecstud.countdown_timer_api.engine.CountDownTimerState
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.DisableSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.UpdateSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import com.girrafeecstud.sos_signal_impl.di.SosSignalFeatureComponent
import com.girrafeecstud.sos_signal_impl.utils.SosSignalUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SosSignalService : Service() {

    companion object {
        private var _sosSignalState = MutableStateFlow<SosSignalState>(SosSignalState.SosSignalDisabled)

        val sosSignalState = _sosSignalState.asStateFlow()
    }

    private val binder = SosSignalServiceBinder()

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var countDownTimerEngine: BaseCountDownTimerEngine

    @Inject
    lateinit var sendSosSignalUseCase: SendSosSignalUseCase

    @Inject
    lateinit var updateSosSignalUseCase: UpdateSosSignalUseCase

    @Inject
    lateinit var disableSosSignalUseCase: DisableSosSignalUseCase

    private var sosSignal: SosSignal? = null

    override fun onCreate() {
        SosSignalFeatureComponent.sosSignalFeatureComponent.inject(this)
        registerObservers()
        Log.i("tag sos", "service created")
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        Log.i("tag sos", "service destroyed")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i("tag", "on bind")
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("tag sos", "service started")
        intent?.let {
            when (it.action) {
                SosSignalUtils.ACTION_SEND_SOS_SIGNAL -> {
                    _sosSignalState.update { SosSignalState.SosSignalPreparing }
                    startForeground(999, getNotification())
                    val sosSignal = it.getParcelableExtra<SosSignal>("sosSignal")
                    // TODO dealing with null safety?
                    if (sosSignal != null) {
                        this.sosSignal = sosSignal
                        prepareSosSignal()
                    }
                }
                SosSignalUtils.ACTION_UPDATE_SOS_SIGNAL -> {
                    val sosSignal = it.getParcelableExtra<SosSignal>("sosSignal")
                    // TODO dealing with null safety?
                    if (sosSignal != null) {
                        updateSosSignal(sosSignal)
                    }
                }
                SosSignalUtils.ACTION_DISABLE_SOS_SIGNAL -> {
                    disableSosSignal()
                }
            }
        }

        return START_STICKY
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("tag", "on unbind")
        return super.onUnbind(intent)
    }

    private fun getNotification(
        title: String = "SOS-сигнал",
        text: String = ""
    ): Notification {

        val sosSignalNotification: Notification.Builder?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = "Foreground Sos Signal"
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
            sosSignalNotification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(com.girrafeecstud.core_ui.R.drawable.ic_bell)
        }
        else {
            sosSignalNotification = Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(com.girrafeecstud.core_ui.R.drawable.ic_bell)
        }

        return sosSignalNotification.build()
    }

    private fun registerObservers() {
        Log.i("tag ctd service", "observe state")
        serviceScope.launch {
            countDownTimerEngine.getCountDownTimerState()
                .onEach { state ->
                    Log.i("tag ctd service", "new state")
                    processCountDownTimerState(state = state)
                }
                .launchIn(serviceScope)
        }
    }

    private fun processCountDownTimerState(state: CountDownTimerState) {
        Log.i("tag sos ctd service", state.toString())
        if (state.millisLeft != null) {
            Log.i("tag", "sos sisgnal ${state.millisLeft}")
            val mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.notify(999, getNotification(text = "SOS-сигнал будет отправлен через: ${state.millisLeft}"))
        }

        if (state.isFinished == true && state.isEnabled && state.isCancelled == false) {

            val mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.notify(999, getNotification(text = "Отправка SOS-сигнала"))
            sendSosSignal(sosSignal = sosSignal)
        }

    }

    private fun prepareSosSignal() {
        countDownTimerEngine.startCountDownTimer()
    }

    private fun sendSosSignal(sosSignal: SosSignal?) {
        Log.i("tag", "ctd broken")
        //TODO null safety
        if (sosSignal == null)
            return
        serviceScope.launch {
            sendSosSignalUseCase(sosSignal = sosSignal)
                .onStart {
                    _sosSignalState.update { SosSignalState.SosSignalSending }
                }
                .collect { result ->
                    when (result) {
                        is BusinessResult.Success -> {
                            _sosSignalState.update {
                                SosSignalState.SosSignalSuccess(sosSignal = sosSignal)
                            }
                        }
                        is BusinessResult.Error -> {
                            _sosSignalState.update {
                                SosSignalState.SosSignalError(sosSignal = sosSignal)
                            }
                            trySendSosSignal(sosSignal = sosSignal)
                        }
                        is BusinessResult.Exception -> {
                            _sosSignalState.update {
                                SosSignalState.SosSignalError(sosSignal = sosSignal)
                            }
                            trySendSosSignal(sosSignal = sosSignal)
                        }
                    }
            }
        }
    }

    private fun updateSosSignal(sosSignal: SosSignal) {
        serviceScope.launch {
            updateSosSignalUseCase(sosSignal = sosSignal)
                .onStart {
                    _sosSignalState.update { SosSignalState.SosSignalUpdating }
                }
                .collect { result ->
                    when (result) {
                        is BusinessResult.Success -> {
                            _sosSignalState.update {
                                SosSignalState.SosSignalSuccess(sosSignal = sosSignal)
                            }
                        }
                        is BusinessResult.Error -> {
                            _sosSignalState.update {
                                SosSignalState.SosSignalError(sosSignal = sosSignal)
                            }
                            trySendSosSignal(sosSignal = sosSignal)
                        }
                        is BusinessResult.Exception -> {
                            _sosSignalState.update {
                                SosSignalState.SosSignalError(sosSignal = sosSignal)
                            }
                            trySendSosSignal(sosSignal = sosSignal)
                        }
                    }
                }
        }
    }

    private fun disableSosSignal() {
        // When disable sos signal on preparing state
        if (sosSignalState.value is SosSignalState.SosSignalPreparing) {
            Log.i("tag sos", "disable sos")
            _sosSignalState.update { SosSignalState.SosSignalDisabling }
            countDownTimerEngine.stopCountDownTimer()
            _sosSignalState.update { SosSignalState.SosSignalDisabled }
            stopSelf()
            return
        }

        serviceScope.launch {
            disableSosSignalUseCase()
                .onStart {
                    _sosSignalState.update { SosSignalState.SosSignalDisabling }
                }
                .collect { result ->
                    when (result) {
                        is BusinessResult.Success -> {
                            _sosSignalState.update { SosSignalState.SosSignalDisabled }
                            stopForeground(true)
                            stopSelf()
                        }
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {}
                    }
                }
        }
    }

    // TODO method for waiting server connection if sos signal
    private fun trySendSosSignal(sosSignal: SosSignal) {

    }

    // TODO method for waiting connection for disabling sos signal
    private fun tryDisableSosSignal() {

    }

    inner class SosSignalServiceBinder : Binder() {
        fun getService() = this@SosSignalService
    }
}