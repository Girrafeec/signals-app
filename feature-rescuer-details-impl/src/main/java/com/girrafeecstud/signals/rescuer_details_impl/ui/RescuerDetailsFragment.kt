package com.girrafeecstud.signals.rescuer_details_impl.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.girrafeecstud.core_ui.extension.*
import com.girrafeecstud.signals.rescuer_details_impl.databinding.FragmentRescuerDetailsBinding
import com.girrafeecstud.signals.rescuer_details_impl.di.component.RescuerDetailsFeatureComponent
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.rescuer_details_api.ui.BaseRescuerDetailsFragment
import com.girrafeecstud.signals.rescuer_details_impl.presentation.RescuerDetailsUiState
import com.girrafeecstud.signals.rescuer_details_impl.presentation.RescuerDetailsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class RescuerDetailsFragment @Inject constructor(

) : BaseFragment() {

    private var _binding: FragmentRescuerDetailsBinding? = null

    private val binding get() = _binding!!

    private val args: RescuerDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val rescuerDetailsViewModel: RescuerDetailsViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        RescuerDetailsFeatureComponent.rescuerDetailsFeatureComponent.rescuerDetailsComponent().build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRescuerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRescuerDetails(rescuer = arguments?.getParcelable<Rescuer>("rescuerDetails"))
        args.rescuerId?.let { rescuerId ->
            rescuerDetailsViewModel.fetchRescuerDetails(rescuerId = rescuerId)
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
                rescuerDetailsViewModel.state
                    .onEach { state ->
                        setState(state = state)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun setState(state: UiState) {
        state as RescuerDetailsUiState

        if (state.isLoading) {
            binding.progressBar.showView()
            binding.rescuerDetailsContentLayout.hideView()
            binding.rescuerDetailsContentLayout.disable()
        }
        else {
            binding.progressBar.removeView()
            binding.rescuerDetailsContentLayout.showView()
            binding.rescuerDetailsContentLayout.enable()
        }

        if (state.rescuerDetails != null)
            setRescuerDetails(rescuer = state.rescuerDetails)
    }

    private fun setRescuerDetails(rescuer: Rescuer?) {
        if (rescuer == null)
            return
        binding.rescuerProfileImage.loadAndSetImage(url = rescuer.rescuerProfileImageUrl)
        binding.rescuerName.text = "${rescuer.rescuerFirstName} ${rescuer.rescuerLastName}"
    }
}
