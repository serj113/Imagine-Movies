package com.serj113.presentation.list.itemviews

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.serj113.common.presentation.adapter.bindable.BindableItemView
import com.serj113.imaginemovies.presentation_list.R
import com.serj113.imaginemovies.presentation_list.databinding.PopularMovieItemViewBinding

class PopularMovieItemView :
    BindableItemView<PopularMovieItemViewBinding, PopularMovieItemView.State>() {

    override val viewType: Int = PopularMovieItemView::javaClass.hashCode()
    override val state = State()

    override fun bind(binding: PopularMovieItemViewBinding) {
        binding.tvTitle.text = state.movieTitle
        binding.tvRate.text = state.movieRating
        Glide
            .with(binding.root)
            .load(state.movieImageUrl)
            .into(binding.ivPoster)
        binding.root.setOnClickListener {
            state.onClick?.invoke()
        }
    }

    override fun createBinding(parent: ViewGroup): PopularMovieItemViewBinding {
        val viewBinding = PopularMovieItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        viewBinding.ivPoster.background = GradientDrawable().apply {
            setColor(parent.context.resources.getColor(R.color.white))
            setStroke(1, Color.BLACK)
            cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12f, parent.context.resources.displayMetrics)
        }
        viewBinding.ivPoster.clipToOutline = true
        return viewBinding
    }

    class State {
        var movieTitle: String = ""
        var movieRating: String = ""
        var movieImageUrl: String = ""
        var onClick: (() -> Unit)? = null
    }
}