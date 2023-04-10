/* Created by Girrafeec */

package com.girrafeecstud.on_board.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.on_board.R
import com.girrafeecstud.on_board.databinding.OnBoardPageItemBinding
import javax.inject.Inject

class OnBoardSlidesAdapter @Inject constructor(

) : RecyclerView.Adapter<OnBoardSlidesAdapter.OnBoardPageItemViewHolder>() {

    private val slides = listOf(
        OnBoardSlide(
            title = "Будьте готовы к непредвиденным ситуациям",
            description = "Signals - ваш надежный помощник в экстренных ситуациях. Вы можете вызвать на помощь и отправить SOS-сигнал в одно касание",
            icon = com.girrafeecstud.core_ui.R.drawable.onboard_first
        ),
        OnBoardSlide(
            title = "Безопасность - наш главный приоритет",
            description = "Signals обеспечивает безопасность ваших близких и вас самих. Мы быстро свяжем вас с людьми рядом, которые готовы помочь",
            icon = com.girrafeecstud.core_ui.R.drawable.onboarding_two
        ),
        OnBoardSlide(
            title = "Спокойствие и уверенность в экстренной ситуации",
            description = "С помощью Signals вы можете отслеживать статус вашего вызова на помощь и получить информацию о людях, которые вышли на помощь",
            icon = com.girrafeecstud.core_ui.R.drawable.onboard_third
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardPageItemViewHolder {
        return OnBoardPageItemViewHolder(
            OnBoardPageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return slides.size
    }

    override fun onBindViewHolder(holder: OnBoardPageItemViewHolder, position: Int) {
        holder.bind(slides[position])
    }

    inner class OnBoardPageItemViewHolder(
        private val binding: OnBoardPageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(slide: OnBoardSlide) {
            binding.slideImage.setImageResource(slide.icon)
            binding.textDescription.text = slide.description
            binding.textTitle.text = slide.title
        }

    }

}