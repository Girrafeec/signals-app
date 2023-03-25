package com.girrafeecstud.signals.signal_details_impl.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.core_ui.extension.loadAndSetImage
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.signal_details_api.ui.ISignalDetailsFragment
import com.girrafeecstud.signals.signal_details_impl.databinding.FragmentSignalDetailsBinding
import com.girrafeecstud.signals.signal_details_impl.di.SignalDetailsFeatureComponent
import com.girrafeecstud.signals.signal_details_impl.presentation.SignalDetailsUiState
import com.girrafeecstud.signals.signal_details_impl.presentation.SignalDetailsViewModel
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignalDetailsFragment @Inject constructor(

) : ISignalDetailsFragment() {

    companion object {
        var isShown = false
        var signalId: String? = null
    }

    private var _binding: FragmentSignalDetailsBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var signalsEngine: SignalsEngine

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val signalDetailsViewModel: SignalDetailsViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SignalDetailsFeatureComponent.signalDetailsFeatureComponent.injectSignalsDetailsFragment(this)
//        SignalDetailsFeatureComponent.signalDetailsFeatureComponent.signalDetailsComponent().build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignalDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        isShown = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isShown = true
        arguments?.getString("signalId")?.let { signalId ->
            Log.i("tag", "signal id det $signalId")
            SignalDetailsFragment.signalId = signalId
            signalDetailsViewModel.fetchSignalDetails(signalId = signalId)
        }
    }



    override fun setListeners() {
//        binding.showRescuerLocationBtn.setOnClickListener {
//            Log.i("tag", "show rescuer on map")
//        }
//        binding.callRescuerBtn.setOnClickListener {
//            Log.i("tag", "call rescuer")
//        }
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signalDetailsViewModel.state
                    .onEach { state ->
                        when (state) {
                            is SignalDetailsUiState.ShowSignalDetails -> {
                                setLoading(isLoading = false)
                                setSignalDetails(signal = state.signal)
                            }
                            SignalDetailsUiState.SignalDetailsLoading -> {
                                setLoading(isLoading = true)
                            }
                            SignalDetailsUiState.SignalDetailsLoadingError -> {}
                            SignalDetailsUiState.SignalDisabled -> {}
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    private fun setSignalDetails(signal: EmergencySignal?) {
        if (signal == null)
            return
        binding.callerProfileImage.loadAndSetImage(url = signal.signalSender.signalSenderProfileImageUrl)
        binding.callerName.text = "${signal.signalSender.signalSenderFirstName} ${signal.signalSender.signalSenderLastName}"
    }

    private fun setLoading(isLoading: Boolean) =
        when (isLoading) {
            true -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.signalDetailsContentLayout.visibility = View.INVISIBLE
                binding.signalDetailsContentLayout.isClickable = false
            }
            false -> {
                binding.progressBar.visibility = View.GONE
                binding.signalDetailsContentLayout.visibility = View.VISIBLE
                binding.signalDetailsContentLayout.isClickable = true
            }
        }
}
