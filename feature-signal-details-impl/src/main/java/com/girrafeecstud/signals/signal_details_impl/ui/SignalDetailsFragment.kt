package com.girrafeecstud.signals.signal_details_impl.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.girrafeecstud.core_ui.extension.hideView
import com.girrafeecstud.core_ui.extension.loadAndSetImage
import com.girrafeecstud.core_ui.extension.removeView
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.signal_details_api.ui.BaseSignalDetailsFragment
import com.girrafeecstud.signals.signal_details_impl.R
import com.girrafeecstud.signals.signal_details_impl.databinding.FragmentSignalDetailsBinding
import com.girrafeecstud.signals.signal_details_impl.di.SignalDetailsFeatureComponent
import com.girrafeecstud.signals.signal_details_impl.presentation.SignalDetailsUiState
import com.girrafeecstud.signals.signal_details_impl.presentation.SignalDetailsViewModel
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignalType
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class SignalDetailsFragment @Inject constructor(

) : BaseFragment() {

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

    private val args: SignalDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("tag sos det", "attached")
//        SignalDetailsFeatureComponent.signalDetailsFeatureComponent.injectSignalsDetailsFragment(this)
        SignalDetailsFeatureComponent.signalDetailsFeatureComponent.signalDetailsComponent().build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("tag sos det", "created")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("tag sos det", "destroyed")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("tag sos det", "create view")
        _binding = FragmentSignalDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        isShown = false
        Log.i("tag sos det", "destroy view")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("tag sos det", "view created")
        isShown = true
        args.signalId?.let { signalId->
            Log.i("tag sos det", "signal id det $signalId")
            SignalDetailsFragment.signalId = signalId
            signalDetailsViewModel.fetchSignalDetails(signalId = signalId)
        }
//        arguments?.getString("signalId")?.let { signalId ->
//            Log.i("tag sos det", "signal id det $signalId")
//            SignalDetailsFragment.signalId = signalId
//            signalDetailsViewModel.fetchSignalDetails(signalId = signalId)
//        }
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
        if (!signal.emergencySignalDescription.equals("") || signal.emergencySignalDescription != null)
            binding.signalDescription?.text = "\"${signal.emergencySignalDescription}\""
        else
            binding.signalDescription?.hideView()

        if (signal.emergencySignalType == EmergencySignalType.DEFAULT_SOS_SIGNAL)
            binding.signalTypeImage?.setImageResource(com.girrafeecstud.core_ui.R.drawable.ic_bell_red_big)
        if (signal.emergencySignalType == EmergencySignalType.HEART_ATTACK_SIGNAL)
            binding.signalTypeImage?.setImageResource(com.girrafeecstud.core_ui.R.drawable.ic_heart_red)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            val dateTime = LocalDateTime.parse(signal.signalStartTimestamp, formatter)
            binding.signalStartTimestamp.text = "${dateTime.hour}:${dateTime.minute}"
        }
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
