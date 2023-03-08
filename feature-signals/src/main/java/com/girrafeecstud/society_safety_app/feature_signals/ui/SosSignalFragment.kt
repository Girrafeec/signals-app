package com.girrafeecstud.society_safety_app.feature_signals.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_signals.databinding.FragmentSosSignalBinding
import com.girrafeecstud.society_safety_app.feature_signals.di.SignalsFeatureComponent
import com.girrafeecstud.society_safety_app.feature_signals.presentation.SosSignalUiState
import com.girrafeecstud.society_safety_app.feature_signals.presentation.SosSignalViewModel
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignalType
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosSignalFragment : BaseFragment() {

    private var _binding: FragmentSosSignalBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val sosSignalViewModel: SosSignalViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        //TODO fix!!!
        SignalsFeatureComponent.signalsFeatureComponent.sosSignalComponent().build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSosSignalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sosSignalViewModel.state
                    .onEach {state ->
                        when (state) {
                            SosSignalUiState.ChooseSignalData -> chooseSosSignal()
                            SosSignalUiState.Error -> {
                                TODO()
                            }
                            SosSignalUiState.SignalCountDownDialog -> sosSignalDialog()
                            SosSignalUiState.SignalSending -> {}
                            SosSignalUiState.SignalSent -> {
                                (parentFragment?.parentFragment as SignalsFlowFragment).openSosMapScreen()
                            }
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun setListeners() {
        binding.sosBtn.setOnClickListener {
            sendSosSignal()
//            showSosDialog()
        }
    }

    private fun chooseSosSignal() {
        // TODO disable dialog if it is enabled
    }

    private fun sosSignalDialog() {
//        val dialog = SosSignalCountdownDialogFragment()
//        dialog.show(requireActivity().supportFragmentManager, "SosSignalCountdownDialogFragment")
    }

    private fun sendSosSignal() {
        var sosSignalType = SosSignalType.DEFAULT_SOS_SIGNAL
        binding.sosSignalCategoryRadioGroup.checkedRadioButtonId
        when (binding.sosSignalCategoryRadioGroup.checkedRadioButtonId) {
            binding.defaultRadio.id -> {
                sosSignalType = SosSignalType.DEFAULT_SOS_SIGNAL
            }
            binding.heartAttackRadio.id -> {
                sosSignalType = SosSignalType.HEARD_ATTACK_SIGNAL
            }
        }

        val sosSignal = SosSignal(
            signalTitle = "Default sos signal title",
            signalDescription = binding.sosSignalDescription.text.toString(),
            signalType = sosSignalType
        )
        sosSignalViewModel.sendSosSignal(context = requireActivity().applicationContext, sosSignal = sosSignal)
    }

}