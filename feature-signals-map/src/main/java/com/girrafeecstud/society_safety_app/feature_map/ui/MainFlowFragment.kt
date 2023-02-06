package com.girrafeecstud.society_safety_app.feature_map.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import com.girrafeecstud.society_safety_app.core_base.ui.base.BaseFlowFragment
import com.girrafeecstud.society_safety_app.core_preferences.di.CorePreferencesComponent
import com.girrafeecstud.society_safety_app.feature_map.R
import com.girrafeecstud.society_safety_app.feature_map.databinding.FragmentMainFlowBinding
import com.girrafeecstud.society_safety_app.feature_map.di.DaggerMainComponent_MainDependenciesComponent
import com.girrafeecstud.society_safety_app.feature_map.di.MainComponent
import com.girrafeecstud.society_safety_app.feature_map.presentation.SettingsViewModel
import javax.inject.Inject

class MainFlowFragment: BaseFlowFragment(
    R.id.nav_host_fragment_main_container
) {

    private var _binding: FragmentMainFlowBinding? = null

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
        _binding = FragmentMainFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainActionBar.logOutImgBtn.setOnClickListener { logOut() }
    }



    // TODO добавить что-то?
    override fun setUpNavigation() {

    }

    private fun logOut() {
        settinsViewModel.logOut()
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.girrafeecstud.society_safety_app/auth_flow_fragment".toUri())
            .build()
        //navController.navigate(request)
        findNavController().navigate(request)
    }

}