package com.girrafeecstud.society_safety_app.feature_signals.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_signals.R
import com.girrafeecstud.society_safety_app.feature_signals.databinding.FragmentSosSignalBinding
import com.girrafeecstud.society_safety_app.feature_signals.di.SignalsFeatureComponent
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
        binding.sosBtn.setOnClickListener { sosSignalEngine.startSosSignal(requireActivity().applicationContext) }
    }
}