package com.girrafeecstud.signals.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.girrafeecstud.signals.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.signals.core_base.ui.base.BaseFlowFragment
import com.girrafeecstud.signals.feature_map.MapsFlowArgs
import com.girrafeecstud.signals.feature_map.R
import com.girrafeecstud.signals.feature_map.databinding.FragmentMapsFlowBinding
import com.girrafeecstud.signals.feature_map.di.MainComponent
import com.girrafeecstud.signals.feature_map.navigation.MapsFlowDestination
import com.girrafeecstud.signals.feature_map.navigation.MapsFlowNavigator
import com.girrafeecstud.signals.feature_map.navigation.ToMapScreenNavigable
import com.girrafeecstud.signals.feature_map.presentation.SettingsViewModel
import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapsFlowFragment: BaseFlowFragment(
    R.id.nav_host_fragment_main_container
), ToMapScreenNavigable {

    private val mapFlowArgs: MapsFlowArgs by navArgs()

    private val navigator = MapsFlowNavigator()

    private var _binding: FragmentMapsFlowBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var sosSignalEngine: SosSignalEngine

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
        Log.i("mapsFlow", "create view")
        _binding = FragmentMapsFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        Log.i("mapsFlow", "destroy view")
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    // TODO добавить что-то?
    override fun setUpNavigation() {
        Log.i("mapsFlow", "setUpNavigation")
        navigator.setNavController(navController)
        mapFlowArgs.defaultScreen.let { screen ->
            when(screen) {
                DefaultMapsFlowScreen.SIGNALS_MAP_SCREEN ->
                    navigator.setStartDestination(
                        destination = MapsFlowDestination.SignalsMapFragment()
                    )
                DefaultMapsFlowScreen.SOS_SIGNAL_MAP_SCREEN -> {
                    navigator.setStartDestination(
                        destination = MapsFlowDestination.SosMapFragment()
                    )
                }
            }
        }
    }

    override fun setListeners() {
        binding.mainActionBar.logOutImgBtn.setOnClickListener { logOut() }
        binding.sosBtn.setOnClickListener { openSosFragment() }
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sosSignalEngine.getSosSignalState()
                    .filter { state ->
                        state is SosSignalState.SosSignalDisabled ||
                                state is SosSignalState.SosSignalSuccess ||
                                state is SosSignalState.SosSignalError
                    }
                    .onEach { state ->
                        if (state is SosSignalState.SosSignalDisabled) {
                            showSignalButtons()
                        }
                        if (state is SosSignalState.SosSignalSuccess ||
                                state is SosSignalState.SosSignalError)
                            hideSignalButtons()
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
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

    private fun hideSignalButtons() {
        binding.sosBtn.visibility = View.GONE
        binding.watchOverMeBtn.visibility = View.GONE
    }

    private fun showSignalButtons() {
        binding.sosBtn.visibility = View.VISIBLE
        binding.watchOverMeBtn.visibility = View.VISIBLE
    }

}