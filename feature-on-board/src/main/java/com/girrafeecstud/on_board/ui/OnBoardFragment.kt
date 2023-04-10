/* Created by Girrafeec */

package com.girrafeecstud.on_board.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.on_board.databinding.FragmentOnBoardBinding
import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource
import com.girrafeecstud.on_board.di.OnBoardFeatureComponent
import com.girrafeecstud.on_board.navigation.OnBoardDestination
import com.girrafeecstud.on_board.navigation.ToOnBoardScreenNavigable
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardFragment : BaseFragment() {

    @Inject
    lateinit var slidesAdapter: OnBoardSlidesAdapter

    @Inject
    lateinit var onBoardDataSource: OnBoardSharedPreferencesDataSource

    private var _binding: FragmentOnBoardBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        OnBoardFeatureComponent.onBoardFeatureComponent.onBoardComponent().build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardBinding.inflate(inflater, container, false)
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
        binding.onBoardViewPager.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.onBoardViewPager.adapter = slidesAdapter
        binding.indicator.setViewPager(binding.onBoardViewPager)
        binding.onBoardViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position != slidesAdapter.itemCount - 1) {
                        binding.nextButtonText.text = "Далее"
                        binding.nextButton.setOnClickListener {
                            binding.onBoardViewPager.currentItem.let {
                                binding.onBoardViewPager.setCurrentItem(it+1, false)
                            }
                        }
                        return
                    }
                    binding.nextButtonText.text = "Начать"
                    binding.nextButton.setOnClickListener {
//                        viewLifecycleOwner.lifecycleScope.launch {
//                            saveOnboarding()
//                        }
                        (parentFragment?.parentFragment as ToOnBoardScreenNavigable)
                            .navigateToScreen(destination = OnBoardDestination.PermissionsFragment)
                    }
                }
            }
        )
    }

    suspend fun saveOnboarding() {
        onBoardDataSource.setOnBoardStatus(onBoardFinished = true)
    }

}