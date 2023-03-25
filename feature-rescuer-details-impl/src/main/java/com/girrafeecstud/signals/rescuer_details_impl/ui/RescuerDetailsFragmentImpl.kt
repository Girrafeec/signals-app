package com.girrafeecstud.signals.rescuer_details_impl.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.signals.rescuer_details_impl.databinding.FragmentImplRescuerDetailsBinding
import com.girrafeecstud.signals.rescuer_details_impl.di.RescuerDetailsFeatureComponent
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.core_ui.extension.loadAndSetImage
import com.girrafeecstud.signals.rescuer_details_api.ui.RescuerDetailsFragment
import javax.inject.Inject

class RescuerDetailsFragmentImpl @Inject constructor(

) : RescuerDetailsFragment() {

    private var _binding: FragmentImplRescuerDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        RescuerDetailsFeatureComponent.rescuerDetailsFeatureComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImplRescuerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRescuerDetails(rescuer = arguments?.getParcelable<Rescuer>("rescuerDetails"))
        setListeners()
    }



    override fun setListeners() {
//        binding.showRescuerLocationBtn.setOnClickListener {
//            Log.i("tag", "show rescuer on map")
//        }
//        binding.callRescuerBtn.setOnClickListener {
//            Log.i("tag", "call rescuer")
//        }
    }

    private fun setRescuerDetails(rescuer: Rescuer?) {
        if (rescuer == null)
            return
        binding.rescuerProfileImage.loadAndSetImage(url = rescuer.rescuerProfileImageUrl)
        binding.rescuerName.text = "${rescuer.rescuerFirstName} ${rescuer.rescuerLastName}"
    }
}
