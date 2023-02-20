package com.girrafeecstud.society_safety_app.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFragment
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentSosSignalMapBinding
import com.girrafeecstud.society_safety_app.feature_map.di.MainComponent
import com.girrafeecstud.society_safety_app.feature_map.presentation.SosSignalMapViewModel
import kotlinx.coroutines.flow.collect
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
        MainComponent.mainComponent.injectSosMap(this)
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

    override fun registerObservers() {
//        lifecycleScope.launchWhenStarted {
//            sosSignalMapViewModel.sos.collect { result ->
//                when (result) {
//                    is BusinessResult.Success -> {
//                        Toast.makeText(requireContext(), "sos sent successfully" , Toast.LENGTH_SHORT).show()
//                    }
//                    is BusinessResult.Error -> {
//
//                    }
//                    is BusinessResult.Exception -> {
//
//                    }
//                }
//            }
//        }
    }
}