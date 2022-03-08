package com.serj113.presentation.list.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.serj113.common.presentation.adapter.bindable.BindableItemView
import com.serj113.presentation.list.databinding.PopularMovieItemViewBinding

class PopularMovieItemView :
    BindableItemView<PopularMovieItemViewBinding, PopularMovieItemView.State>() {

    override val viewType: Int = PopularMovieItemView::javaClass.hashCode()
    override val state = State()

    override fun bind(binding: PopularMovieItemViewBinding, state: State) {
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
        return PopularMovieItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    class State {
        var movieTitle: String = ""
        var movieRating: String = ""
        var movieImageUrl: String = ""
        var onClick: (() -> Unit)? = null
    }
}