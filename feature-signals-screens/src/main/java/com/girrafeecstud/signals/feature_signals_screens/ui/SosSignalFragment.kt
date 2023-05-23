package com.girrafeecstud.signals.feature_signals_screens.ui

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_components.vibrate
import com.girrafeecstud.core_ui.extension.addOnKeyboardVisibilityListener
import com.girrafeecstud.core_ui.extension.hideView
import com.girrafeecstud.core_ui.extension.isKeyboardVisible
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.signals.core_base.base.unique
import com.girrafeecstud.signals.feature_signals_screens.databinding.FragmentSosSignalBinding
import com.girrafeecstud.signals.feature_signals_screens.di.SignalsScreensFeatureComponent
import com.girrafeecstud.signals.feature_signals_screens.navigation.SignalsFlowDestination
import com.girrafeecstud.signals.feature_signals_screens.navigation.ToSosScreenNavigable
import com.girrafeecstud.signals.feature_signals_screens.presentation.SignalSharedViewModel
import com.girrafeecstud.signals.feature_signals_screens.presentation.SosSignalUiState
import com.girrafeecstud.signals.feature_signals_screens.presentation.SosSignalViewModel
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignalType
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosSignalFragment : BaseFragment(), SosTypeClickEvent {

    private var _binding: FragmentSosSignalBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val sosSignalViewModel: SosSignalViewModel by viewModels {
        mainViewModelFactory
    }

    private val signalSharedViewModel: SignalSharedViewModel by viewModels {
        mainViewModelFactory
    }

    @Inject
    lateinit var sosTypesAdapter: SosTypesAdapter

    override fun onAttach(context: Context) {
        //TODO fix!!!
        SignalsScreensFeatureComponent.signalsFeatureComponent.sosSignalComponent().build().inject(this)
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
        Log.i("tag sos fr", signalSharedViewModel.hashCode().toString())
        initRecView()
        Log.i("tag sos", "fragment view created")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("tag sos", "fragment destroyed")
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
        Log.i("tag sos", "fragment started")
    }

    override fun onStop() {
        super.onStop()
        Log.i("tag sos", "fragment stopped")
    }

    override fun registerObservers() {
        var previousState: SosSignalUiState? = null
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                sosSignalViewModel.state
                    .unique()
                    .onEach {state ->
                        Log.i("tag sos fragment pr st", previousState.toString())
                        if (previousState == state)
                            return@onEach
                        previousState = state
                        Log.i("tag sos fragment pr new", previousState.toString())
                        Log.i("tag sos fragment state", state.toString())
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

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                signalSharedViewModel.state
                    .onEach { state ->
                        if (state.signalCancelled)
                            sosSignalViewModel.disableSos(requireContext())
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)

            }
        }
    }

    override fun setListeners() {
        binding.sosBtn.setOnLongClickListener {
            sendSosSignal()
            vibrate()
            true
        }
//        binding.sosSignalDescription.setOnFocusChangeListener { view, hasFocus ->
//            if (hasFocus && binding.root.isKeyboardVisible())
//                binding.sosBtn.hideView()
//            else
//                binding.sosBtn.showView()
//
//        }
//        binding.root.addOnKeyboardVisibilityListener { isVisible ->
//            Log.i("tag", "keyboard isVisible: $isVisible")
//            if (isVisible)
//                binding.sosBtn.hideView()
//            else
//                binding.sosBtn.showView()
//        }
    }

    override fun onSosTypeSelected(type: SosType) {
        Log.i("tag sos", "selected ${type.typeName}")
        sosTypesAdapter.setSelectedSosType(typeId = type.typeId)
    }

    private fun initRecView() {
        binding.sosSignalTypes.let {
            sosTypesAdapter.listener = this
            it.adapter = sosTypesAdapter
            it.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            it.layoutManager = LinearLayoutManager(
                requireActivity().applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun chooseSosSignal() {
        // TODO disable dialog if it is enabled
    }

    private fun sosSignalDialog() {
        Log.i("tag sos", "show dialog")
        (parentFragment?.parentFragment as ToSosScreenNavigable)
            .navigateToScreen(destination = SignalsFlowDestination.SosCountDownDialog())
    }

    private fun sendSosSignal() {
        val sosType = sosTypesAdapter.selectedType

        val signalTitle = when(sosType.type) {
            SosSignalType.DEFAULT_SOS_SIGNAL -> "Мне нужна помощь!"
            SosSignalType.HEART_ATTACK_SIGNAL -> "Мне нужна помощь, возможно, инфаркт!"
        }

        val sosSignal = SosSignal(
            signalTitle = signalTitle,
            signalDescription = binding.sosSignalDescription.text.toString(),
            signalType = sosType.type
        )
        sosSignalViewModel.sendSosSignal(context = requireActivity().applicationContext, sosSignal = sosSignal)
    }

}