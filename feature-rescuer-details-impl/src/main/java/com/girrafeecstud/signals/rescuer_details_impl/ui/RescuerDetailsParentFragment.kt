package com.girrafeecstud.signals.rescuer_details_impl.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.girrafeecstud.signals.rescuer_details_api.ui.BaseRescuerDetailsFragment
import com.girrafeecstud.signals.rescuer_details_impl.R
import com.girrafeecstud.signals.rescuer_details_impl.databinding.FragmentParentRescuerDetailsBinding
import com.girrafeecstud.signals.rescuer_details_impl.di.component.RescuerDetailsFeatureComponent
import javax.inject.Inject

class RescuerDetailsParentFragment @Inject constructor(

) : BaseRescuerDetailsFragment() {

    private var _binding: FragmentParentRescuerDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        RescuerDetailsFeatureComponent.rescuerDetailsFeatureComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentParentRescuerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragmentId = R.id.nav_host_fragment_rescuer_details_container

        val navHostFragment =
            childFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment

        navController = navHostFragment.navController

        arguments?.getString("rescuerId")?.let { rescuerId ->
            Log.i("tag resc det", "rescuerId $rescuerId")
            navController.navigate(
                RescuerDetailsFragmentDirections.actionGlobalSignalDetails().setRescuerId(rescuerId)
            )
        }
    }
}