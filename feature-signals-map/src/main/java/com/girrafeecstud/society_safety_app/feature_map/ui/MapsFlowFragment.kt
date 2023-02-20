package com.girrafeecstud.society_safety_app.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFlowFragment
import com.girrafeecstud.society_safety_app.feature_map.MapsFlowArgs
import com.girrafeecstud.society_safety_app.feature_map.R
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentMapsFlowBinding
import com.girrafeecstud.society_safety_app.feature_map.di.MainComponent
import com.girrafeecstud.society_safety_app.feature_map.navigation.MapsFlowDestination
import com.girrafeecstud.society_safety_app.feature_map.navigation.MapsFlowNavigator
import com.girrafeecstud.society_safety_app.feature_map.navigation.ToMapScreenNavigable
import com.girrafeecstud.society_safety_app.feature_map.presentation.SettingsViewModel
import com.girrafeecstud.society_safety_app.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.society_safety_app.navigation.ToFlowNavigable
import com.girrafeecstud.society_safety_app.navigation.destination.FlowDestination
import javax.inject.Inject

class MapsFlowFragment: BaseFlowFragment(
    R.id.nav_host_fragment_main_container
), ToMapScreenNavigable {

    private val mapFlowArgs: MapsFlowArgs by navArgs()

    private val navigator = MapsFlowNavigator()

    private var _binding: FragmentMapsFlowBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val settinsViewModel: SettingsViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        MainComponent.init(
//            dependencies = DaggerMainComponent_MainDependenciesComponent
//                .builder()
//                .corePreferencesApi(CorePreferencesComponent.corePreferencesComponent)
//                .locationTrackerApi(LocationTrackerComponent.locationTrackerComponent)
//                .build()
//        )
        MainComponent.mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainActionBar.logOutImgBtn.setOnClickListener { logOut() }
        setListeners()
    }

    // TODO добавить что-то?
    override fun setUpNavigation() {
        navigator.setNavController(navController)
        mapFlowArgs.defaultScreen.let { screen ->
            when(screen) {
                DefaultMapsFlowScreen.SIGNALS_MAP_SCREEN ->
                    navigator.setStartDestination(
                        destination = MapsFlowDestination.MapsFragment()
                    )
                DefaultMapsFlowScreen.SOS_SIGNAL_MAP_SCREEN ->
                    navigator.setStartDestination(
                        destination = MapsFlowDestination.SosSignalMapsFragment()
                    )
            }
        }
    }

    override fun setListeners() {
        binding.sosBtn.setOnClickListener { openSosFragment() }
    }

    override fun navigateToScreen(destination: MapsFlowDestination) {
        navigator.navigateToDestination(destination = destination)
    }

    private fun logOut() {
        settinsViewModel.logOut()
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.girrafeecstud.society_safety_app/auth_flow_fragment".toUri())
            .build()
        //navController.navigate(request)
        findNavController().navigate(request)
    }

    private fun openSosFragment() {
        (requireActivity() as ToFlowNavigable)
            .navigateToScreen(destination = FlowDestination.SignalsFlow())
    }

    private fun openWatchOverMeScreen() {
        (requireActivity() as ToFlowNavigable)
            .navigateToScreen(destination = FlowDestination.SignalsFlow())
    }

}