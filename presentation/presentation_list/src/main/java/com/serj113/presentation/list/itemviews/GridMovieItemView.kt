package com.serj113.presentation.list.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.serj113.imaginemovies.common.presentation.adapter.bindable.BindableItemView
import com.serj113.imaginemovies.presentation_list.databinding.MovieListItemBinding

class GridMovieItemView :
    BindableItemView<MovieListItemBinding, GridMovieItemView.State>() {
    override val viewType: Int = GridMovieItemView::class.java.hashCode()

    override val state: State = State()

    override fun bind(binding: MovieListItemBinding) {
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

    override fun createBinding(parent: ViewGroup): MovieListItemBinding {
        return MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )
    }

    class State {
        var movieTitle: String = ""
        var movieRating: String = ""
        var movieImageUrl: String = ""
        var onClick: (() -> Unit)? = null
    }
}
