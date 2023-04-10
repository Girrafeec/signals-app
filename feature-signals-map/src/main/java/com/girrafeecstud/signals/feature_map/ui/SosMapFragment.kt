package com.girrafeecstud.signals.feature_map.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girrafeecstud.signals.rescuer_details_api.ui.BaseRescuerDetailsFragment
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedState
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedStateEngine
import com.girrafeecstud.signals.rescuers_list_api.ui.RescuersListFragment
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.signals.feature_map.R
import com.girrafeecstud.signals.feature_map.databinding.FragmentSosMapBinding
import com.girrafeecstud.signals.feature_map.di.MainComponent
import com.girrafeecstud.signals.feature_map.navigation.MapsFlowDestination
import com.girrafeecstud.signals.feature_map.navigation.ToMapScreenNavigable
import com.girrafeecstud.signals.feature_map.presentation.SignalsMapSharedViewModel
import com.girrafeecstud.signals.feature_map.presentation.SosMapSharedViewModel
import com.girrafeecstud.signals.feature_map.presentation.SosMapUIState
import com.girrafeecstud.signals.feature_map.presentation.SosMapViewModel
import com.girrafeecstud.signals.feature_map.presentation.shared_map.MapSharedViewModel
import com.girrafeecstud.signals.rescuer_details_api.utils.RescuerDetailsFeatureUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosMapFragment : BaseFragment() {

    @Inject
    lateinit var rescuersListFragment: RescuersListFragment

    @Inject
    lateinit var rescuerDetailsFragment: BaseRescuerDetailsFragment

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val sosMapViewModel: SosMapViewModel by viewModels {
        mainViewModelFactory
    }

    private val mapSharedViewModel: MapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    private val sosMapSharedViewModel: SosMapSharedViewModel by viewModels {
        mainViewModelFactory
    }

    @Inject
    lateinit var rescuersListSharedStateEngine: RescuersListSharedStateEngine

    private var _binding: FragmentSosMapBinding? = null

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
        _binding = FragmentSosMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        rescuersListSharedStateEngine.closeRescuerDetails()
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("sosMap", "view created")
        loadRescuersListFragment(savedInstanceState = savedInstanceState)

    }

    override fun setListeners() {
        binding.disableSosBtn.setOnClickListener {
            sosMapViewModel.disableSosSignal(requireActivity().applicationContext)
        }
        binding.emergencyCallBtn.setOnClickListener {
            Log.i("tag ыщы", "clicked on emergency call")
            val intent = Intent(Intent.ACTION_CALL)
            intent.setData(Uri.parse("tel:" + "112"))
            requireActivity().startActivity(intent)
        }
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sosMapViewModel.state
                    .onEach { state ->
                        when (state) {
                            is SosMapUIState.DrawRescuersLocations ->
                                mapSharedViewModel.drawRescuersLocation(rescuers = state.rescuers)
                            SosMapUIState.SosErrorSentMessage -> showErrorMessage()
                            SosMapUIState.SosSuccessSentMessage -> showSuccessMessage()
                            SosMapUIState.SosDisabled -> {
                                mapSharedViewModel.clearRescuersLocation()
                                (parentFragment?.parentFragment as ToMapScreenNavigable).navigateToScreen(destination = MapsFlowDestination.SignalsMapFragment())
                            }
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)

                sosMapSharedViewModel.state
                    .onEach { state ->
                        if (state.rescuerDetails != null)
                            showRescuerDetails(rescuer = state.rescuerDetails)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                rescuersListSharedStateEngine.getState()
                    .onEach { state ->
                        when (state) {
                            RescuersListSharedState.CloseRescuerDetails -> {}
                            is RescuersListSharedState.ShowRescuerDetails -> {
                                showRescuerDetails(rescuer = state.rescuer)
                            }
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    private fun loadRescuersListFragment(savedInstanceState: Bundle?) {
        Log.i("sosMap", "load llF")
        if (savedInstanceState == null) {
            childFragmentManager.commit {
                add(R.id.fragment_rescuers_list_container, rescuersListFragment)
            }
        }
    }

    private fun showSuccessMessage() {
        Toast.makeText(requireActivity(), "Sos successfully sent", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorMessage() {
        Toast.makeText(requireActivity(), "Sos error sent", Toast.LENGTH_SHORT).show()
    }

    private fun showRescuerDetails(rescuer: Rescuer) {
        if (childFragmentManager.findFragmentByTag(RescuerDetailsFeatureUtils.FRAGMENT_RESCUER_DETAILS_TAG) != null)
            return
        Log.i("sosMap", "show ${rescuer.rescuerFirstName} details")
        // TODO change tag
        rescuerDetailsFragment.arguments = Bundle().apply {
            this.putString("rescuerId", rescuer.rescuerId)
        }
        childFragmentManager.commit {
            add(
                R.id.rescuer_details_fragment_container,
                rescuerDetailsFragment,
                RescuerDetailsFeatureUtils.FRAGMENT_RESCUER_DETAILS_TAG
            )
        }
        val bottomSheetBehavior = BottomSheetBehavior.from(requireView().findViewById(R.id.rescuer_details_fragment_container))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Handle the slide offset, if needed
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.i("tag", "bottom state $newState")
                // Hide the bottom sheet when it is expanded
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    childFragmentManager.commit {
                        remove(rescuerDetailsFragment)
                    }
                    sosMapSharedViewModel.closeRescuerDetails()
                    rescuersListSharedStateEngine.closeRescuerDetails()
                }
            }
        })
    }

}