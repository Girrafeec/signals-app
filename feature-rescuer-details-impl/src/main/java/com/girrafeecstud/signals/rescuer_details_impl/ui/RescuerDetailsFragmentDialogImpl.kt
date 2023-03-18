package com.girrafeecstud.signals.rescuer_details_impl.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.signals.rescuer_details_api.ui.RescuerDetailsFragmentDialog
import com.girrafeecstud.signals.rescuer_details_impl.R
import com.girrafeecstud.signals.rescuer_details_impl.databinding.FragmentDialogImplRescuerDetailsBinding
import com.girrafeecstud.signals.rescuer_details_impl.di.RescuerDetailsFeatureComponent
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.ui.extension.loadAndSetImage
import javax.inject.Inject

class RescuerDetailsFragmentDialogImpl @Inject constructor(

) : RescuerDetailsFragmentDialog() {

    private var _binding: FragmentDialogImplRescuerDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        RescuerDetailsFeatureComponent.rescuerDetailsFeatureComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogImplRescuerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRescuerDetails(rescuer = arguments?.getParcelable<Rescuer>("rescuerDetails"))
    }

    private fun setRescuerDetails(rescuer: Rescuer?) {
        if (rescuer == null)
            return
        binding.rescuerProfileImage.loadAndSetImage(url = rescuer.rescuerProfileImageUrl)
        binding.rescuerName.text = "${rescuer.rescuerFirstName} ${rescuer.rescuerLastName}"
    }
}
