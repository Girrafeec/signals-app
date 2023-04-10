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
            title = "титул1",
            description = "описание1",
            icon = R.drawable.ic_launcher_background
        ),
        OnBoardSlide(
            title = "титул2",
            description = "описание2",
            icon = R.drawable.ic_launcher_background
        ),
        OnBoardSlide(
            title = "титул3",
            description = "описание3",
            icon = R.drawable.ic_launcher_background
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