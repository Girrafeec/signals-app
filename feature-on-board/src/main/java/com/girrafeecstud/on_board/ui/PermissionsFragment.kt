/* Created by Girrafeec */

package com.girrafeecstud.on_board.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource
import com.girrafeecstud.on_board.databinding.FragmentPermissionsBinding
import com.girrafeecstud.on_board.di.OnBoardFeatureComponent
import com.girrafeecstud.signals.navigation.DefaultMapsFlowScreen
import com.girrafeecstud.signals.navigation.ToFlowNavigable
import com.girrafeecstud.signals.navigation.destination.FlowDestination
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.eazypermissions.common.model.PermissionResult
import com.eazypermissions.coroutinespermission.PermissionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class PermissionsFragment : BaseFragment() {

    @Inject
    lateinit var pagesAdaper: PermissionsPagesAdapter

    @Inject
    lateinit var onBoardDataSource: OnBoardSharedPreferencesDataSource

    private var _binding: FragmentPermissionsBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        OnBoardFeatureComponent.onBoardFeatureComponent.permissionsComponent().build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPermissionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    override fun setListeners() {

    }

    private fun initViewPager() {
        // Disable swiping
        binding.onBoardViewPager.isUserInputEnabled = false
        binding.onBoardViewPager.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.onBoardViewPager.adapter = pagesAdaper
        binding.onBoardViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position != pagesAdaper.itemCount - 1) {
                        val permissionPage = pagesAdaper.pages.get(position)
                        when (permissionPage) {
                            is PermissionPage.PermissionPageItem -> {
                                binding.applyButtonText.text = "Разрешить"
                                binding.applyButton.setOnClickListener {
                                    viewLifecycleOwner.lifecycleScope.launch {
                                        withContext(Dispatchers.Main) {
                                            handlePermissionsResult(
                                                requestPermission(appPermission = permissionPage.permission),
                                                binding.onBoardViewPager
                                            )
                                        }
                                    }
                                }
                            }
                            is PermissionPage.PermissionTitle -> {
                                binding.applyButtonText.text = "Ок"
                                binding.applyButton.setOnClickListener {
                                    binding.onBoardViewPager.currentItem.let {
                                        binding.onBoardViewPager.setCurrentItem(it + 1, false)
                                    }
                                }
                            }
                        }
                        return
                    }
                    binding.applyButtonText.text = "Начать"
                    binding.applyButton.setOnClickListener {
                        viewLifecycleOwner.lifecycleScope.launch {
                            saveOnboarding()
                        }
                        (requireParentFragment().requireParentFragment() as OnBoardFlowFragment).navigateToMapsFlow()
                    }
                }
            }
        )
    }

    private suspend fun requestPermission(appPermission: AppPermission) =
        PermissionManager.requestPermissions(
            fragment = this,
            requestId = appPermission.permissionRequestId,
            permissions = appPermission.permissions
        )

    suspend fun saveOnboarding() {
        onBoardDataSource.setOnBoardStatus(onBoardFinished = true)
        Log.i("tag start", "onboarded true saved")
    }

    private fun handlePermissionsResult(
        permissionsResult: PermissionResult,
        viewPager: ViewPager2
    ) {
        when (permissionsResult) {
            is PermissionResult.PermissionGranted -> {
                Log.i("tag permis", "$permissionsResult granted")
                viewPager.currentItem.let {
                    viewPager.setCurrentItem(it + 1, false)
                }
            }
            is PermissionResult.PermissionDenied -> {
                Log.i("tag permis", "$permissionsResult denied")
                val alertDialog = AlertDialog.Builder(requireContext())
                    .setMessage("Для использования приложения необходимо выдать разрешение")
                    .setTitle("Разрешения")
                    .setPositiveButton("ОК") { _, _ ->
                        when (permissionsResult.requestCode) {
                            1 -> {
                                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                    handlePermissionsResult(
                                        requestPermission(
                                            AppPermission.Location,
                                        ),
                                        viewPager
                                    )
                                }
                            }
                            2 -> {
                                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                    handlePermissionsResult(
                                        requestPermission(
                                            AppPermission.PhoneCall,
                                        ),
                                        viewPager
                                    )
                                }
                            }
                            3 -> {
                                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                    handlePermissionsResult(
                                        requestPermission(
                                            AppPermission.Contacts,
                                        ),
                                        viewPager
                                    )
                                }
                            }
                            4 -> {
                                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                    handlePermissionsResult(
                                        requestPermission(
                                            AppPermission.Notifications,
                                        ),
                                        viewPager
                                    )
                                }
                            }
                        }

                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }.create()
                alertDialog.show()
            }
            is PermissionResult.ShowRational -> {
                Log.i("tag permis", "$permissionsResult show rat")
                val alertDialog = AlertDialog.Builder(requireContext())
                    .setMessage("Для использования приложения необходимо выдать разрешение")
                    .setTitle("Разрешения")
                    .setPositiveButton("ОК") { _, _ ->
                        when (permissionsResult.requestCode) {
                            1 -> {
                                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                    handlePermissionsResult(
                                        requestPermission(
                                            AppPermission.Location,
                                        ),
                                        viewPager
                                    )
                                }
                            }
                            2 -> {
                                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                    handlePermissionsResult(
                                        requestPermission(
                                            AppPermission.PhoneCall,
                                        ),
                                        viewPager
                                    )
                                }
                            }
                            3 -> {
                                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                    handlePermissionsResult(
                                        requestPermission(
                                            AppPermission.Contacts,
                                        ),
                                        viewPager
                                    )
                                }
                            }
                            4 -> {
                                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                    handlePermissionsResult(
                                        requestPermission(
                                            AppPermission.Notifications,
                                        ),
                                        viewPager
                                    )
                                }
                            }
                        }

                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }.create()
                alertDialog.show()
            }
            is PermissionResult.PermissionDeniedPermanently -> {
                Log.i("tag permis", "$permissionsResult denied perm")
                Log.i("tag permis", "${permissionsResult.permanentlyDeniedPermissions} denied perm")
            }
        }
    }

}