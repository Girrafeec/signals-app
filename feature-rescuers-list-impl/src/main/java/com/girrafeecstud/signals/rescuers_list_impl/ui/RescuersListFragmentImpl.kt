package com.girrafeecstud.signals.rescuers_list_impl.ui

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedStateEngine
import com.girrafeecstud.signals.rescuers_list_api.ui.RescuersListFragment
import com.girrafeecstud.signals.rescuers_list_impl.databinding.FragmentImplRescuersListBinding
import com.girrafeecstud.signals.rescuers_list_impl.di.RescuersListFeatureComponent
import com.girrafeecstud.signals.rescuers_list_impl.presentation.RescuersUiState
import com.girrafeecstud.signals.rescuers_list_impl.presentation.RescuersViewModel
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class RescuersListFragmentImpl @Inject constructor() :
    RescuersListFragment(),
    RescuerClickEvent {

    private var _binding: FragmentImplRescuersListBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var rescuersAdapter: RescuersAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val rescuersViewModel: RescuersViewModel by viewModels {
        mainViewModelFactory
    }

    @Inject
    lateinit var rescuersListSharedStateEngine: RescuersListSharedStateEngine

    override fun onAttach(context: Context) {
        super.onAttach(context)
        RescuersListFeatureComponent.rescuersListFeatureComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImplRescuersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecView()
    }

    override fun setListeners() {
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                rescuersViewModel.state
                    .onEach { state ->
                        when (state) {
                            RescuersUiState.NoRescuers -> {}
                            RescuersUiState.RescuersLoading -> {}
                            is RescuersUiState.ShowRescuersList -> {
                                rescuersAdapter.refreshRescuersList(rescuers = state.rescuers)
                            }
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun onRescuerClick(rescuer: Rescuer) {
        Toast.makeText(
            requireActivity().applicationContext,
            "Clicked on ${rescuer.rescuerFirstName}",
            Toast.LENGTH_SHORT
        ).show()
        rescuersListSharedStateEngine.showRescuerDetails(rescuer = rescuer)
    }

    private fun initRecView() {
        binding.rescuersRecView.let { rescuers ->
            rescuersAdapter.listener = this
            rescuers.adapter = rescuersAdapter
            rescuers.layoutManager = LinearLayoutManager(
                requireActivity().applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }
}