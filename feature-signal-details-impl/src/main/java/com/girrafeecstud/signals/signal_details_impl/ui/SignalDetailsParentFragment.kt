package com.girrafeecstud.signals.signal_details_impl.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.girrafeecstud.signals.signal_details_api.ui.BaseSignalDetailsFragment
import com.girrafeecstud.signals.signal_details_impl.R
import com.girrafeecstud.signals.signal_details_impl.databinding.FragmentParentSignalDetailsBinding
import com.girrafeecstud.signals.signal_details_impl.di.SignalDetailsFeatureComponent
import javax.inject.Inject

class SignalDetailsParentFragment @Inject constructor(

) : BaseSignalDetailsFragment() {

    private var _binding: FragmentParentSignalDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SignalDetailsFeatureComponent.signalDetailsFeatureComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentParentSignalDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragmentId = R.id.nav_host_fragment_signal_details_container

        val navHostFragment =
            childFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment

        navController = navHostFragment.navController

        arguments?.getString("signalId")?.let { signalId ->
            navController.navigate(
                SignalDetailsFragmentDirections.actiobGlobalSignalDetails().setSignalId(signalId)
            )
        }
    }
}