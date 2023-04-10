/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.core_components.intensiveVibrate
import com.girrafeecstud.core_components.vibrate
import com.girrafeecstud.core_ui.extension.disable
import com.girrafeecstud.core_ui.extension.hideView
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.core_ui.ui.BaseDialogFragment
import com.girrafeecstud.signals.core_base.base.milliesToFormattedTimeString
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.feature_signals_screens.R
import com.girrafeecstud.signals.feature_signals_screens.databinding.SosCountdownDialogBinding
import com.girrafeecstud.signals.feature_signals_screens.di.SignalsScreensFeatureComponent
import com.girrafeecstud.signals.feature_signals_screens.navigation.ToSosScreenNavigable
import com.girrafeecstud.signals.feature_signals_screens.presentation.SignalSharedViewModel
import com.girrafeecstud.signals.feature_signals_screens.presentation.SosCountDownUiState
import com.girrafeecstud.signals.feature_signals_screens.presentation.SosCountDownViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosCountDownDialog : BaseDialogFragment<SosCountDownUiState>() {

    private var _binding: SosCountdownDialogBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val sosCountDownViewModel: SosCountDownViewModel by viewModels {
        mainViewModelFactory
    }

    private val signalSharedViewModel: SignalSharedViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SignalsScreensFeatureComponent.signalsFeatureComponent.sosCountDownComponent().build().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("tag sos dial", "destroy")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SosCountdownDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        signalSharedViewModel.resetState()
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("tag sos dialog", signalSharedViewModel.hashCode().toString())
    }

    override fun getTheme(): Int {
        return com.girrafeecstud.core_ui.R.style.FullScreenDialog
    }

    override fun setListeners() {
        binding.cancelSosBtn.setOnClickListener {
            binding.cancelSosBtn.disable()
            signalSharedViewModel.cancelSignal()
            dialog?.dismiss()
        }
        binding.closeDialogBtn.setOnClickListener {

        }
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sosCountDownViewModel.state
                    .onEach { state ->
                        setState(state = state)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun setState(state: SosCountDownUiState) {
        if (state.milliesLeft != null) {
            vibrate()
            binding.countdownText.text = state.milliesLeft.milliesToFormattedTimeString()
        }

        if (state.sosTransmitting) {
            binding.countdownText.hideView()
            binding.sosSendingImage.showView()
            binding.cancelSosBtn.disable()
        }

    }
}