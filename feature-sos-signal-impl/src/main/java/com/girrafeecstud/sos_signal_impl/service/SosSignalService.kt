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
import com.girrafeecstud.sos_signal_impl.R
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

    private var _sosSignalNotification: Notification.Builder? = null

    private val sosSignalNotification get() = _sosSignalNotification!!

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
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i("tag", "on bind")
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                SosSignalUtils.ACTION_SEND_SOS_SIGNAL -> {
                    _sosSignalState.update { SosSignalState.SosSignalPreparing }
                    setupNotification()
                    startForeground(999, sosSignalNotification.build())
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

    private fun setupNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = "Foreground Sos Signal"
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
            _sosSignalNotification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("SOS-сигнал")
                .setSmallIcon(com.girrafeecstud.core_ui.R.drawable.ic_bell)
        }
        else {
            _sosSignalNotification = Notification.Builder(this)
                .setContentTitle("SOS-сигнал")
                .setSmallIcon(com.girrafeecstud.core_ui.R.drawable.ic_bell)
        }
    }

    private fun updateNotification(
        title: String = "SOS-сигнал",
        text: String
    ) {
        _sosSignalNotification
            ?.setContentTitle(title)
            ?.setContentText(text)

        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(999, sosSignalNotification.build())
    }

    private fun registerObservers() {
        serviceScope.launch {
            countDownTimerEngine.getCountDownTimerState()
                .onEach { state ->
                    processCountDownTimerState(state = state)
                }
                .launchIn(serviceScope)
        }
    }

    private fun processCountDownTimerState(state: CountDownTimerState) {
        Log.i("tag ctd", state.toString())
        if (state.millliesLeft != null) {
            Log.i("tag", "sos sisgnal ${state.millliesLeft}")
            updateNotification(text = "SOS-сигнал будет отправлен через: ${state.millliesLeft}")
        }

        if (state.isFinished) {
            updateNotification(text = "Отправка SOS-сигнала")
            sendSosSignal(sosSignal = sosSignal)
        }

    }

    private fun prepareSosSignal() {
        countDownTimerEngine.startCountDownTimer()
    }

    private fun sendSosSignal(sosSignal: SosSignal?) {
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
        if (
            sosSignalState.value is SosSignalState.SosSignalSuccess ||
            sosSignalState.value is SosSignalState.SosSignalError
        ) {
            _sosSignalState.update { SosSignalState.SosSignalDisabling }
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