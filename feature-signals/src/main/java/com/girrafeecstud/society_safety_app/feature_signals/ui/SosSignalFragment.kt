package com.girrafeecstud.society_safety_app.feature_signals.ui

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_signals.R
import com.girrafeecstud.society_safety_app.feature_signals.databinding.FragmentSosSignalBinding
import com.girrafeecstud.society_safety_app.feature_signals.di.SignalsFeatureComponent
import com.girrafeecstud.society_safety_app.feature_signals.presentation.SosSignalViewModel
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignalType
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import javax.inject.Inject

class SosSignalFragment : BaseFragment() {

//    private lateinit var viewModel: SosSignalViewModel

    @Inject
    lateinit var sosSignalEngine: SosSignalEngine

    private var _binding: FragmentSosSignalBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        SignalsFeatureComponent.signalsFeatureComponent.injectSos(this)
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
        setListeners()
    }

    override fun registerObservers() {

    }

    override fun setListeners() {
        binding.sosBtn.setOnClickListener {
            sendSosSignal()
//            showSosDialog()
        }
    }

    private fun showSosDialog() {
        val dialog = SosSignalCountdownDialogFragment()
        dialog.show(requireActivity().supportFragmentManager, "SosSignalCountdownDialogFragment")
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

        sosSignalEngine.startSosSignal(
            requireActivity().applicationContext,
            sosSignal = sosSignal
        )
        (parentFragment?.parentFragment as SignalsFlowFragment).openSosMapScreen()
    }

}