package com.girrafeecstud.society_safety_app.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentSosSignalMapBinding
import com.girrafeecstud.society_safety_app.feature_map.di.MainComponent
import com.girrafeecstud.society_safety_app.feature_map.navigation.MapsFlowDestination
import com.girrafeecstud.society_safety_app.feature_map.navigation.ToMapScreenNavigable
import com.girrafeecstud.society_safety_app.feature_map.presentation.SosSignalMapUIState
import com.girrafeecstud.society_safety_app.feature_map.presentation.SosSignalMapViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosSignalMapFragment : BaseFragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val sosSignalMapViewModel: SosSignalMapViewModel by viewModels {
        mainViewModelFactory
    }

    private var _binding: FragmentSosSignalMapBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        MainComponent.mainComponent.sosMapComponent().build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSosSignalMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObservers()
    }

    override fun setListeners() {
        binding.disableSosBtn.setOnClickListener {
            sosSignalMapViewModel.disableSosSignal(requireActivity().applicationContext)
        }
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sosSignalMapViewModel.state
                    .onEach { state ->
                        when (state) {
                            is SosSignalMapUIState.DrawCurrentLocation -> drawCurrentLocation()
                            is SosSignalMapUIState.DrawDefendersLocation -> drawDefendersLocation()
                            SosSignalMapUIState.SosErrorSentMessage -> showErrorMessage()
                            SosSignalMapUIState.SosSuccessSentMessage -> showSuccessMessage()
                            SosSignalMapUIState.SosSignalDisabled -> {
                                (parentFragment?.parentFragment as ToMapScreenNavigable).navigateToScreen(destination = MapsFlowDestination.MapsFragment())
                            }
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    private fun drawCurrentLocation() {

    }

    private fun drawDefendersLocation() {

    }

    private fun showSuccessMessage() {
        Toast.makeText(requireActivity(), "Sos successfully sent", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorMessage() {
        Toast.makeText(requireActivity(), "Sos error sent", Toast.LENGTH_SHORT).show()
    }
}