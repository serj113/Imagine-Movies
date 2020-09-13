package com.serj113.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serj113.model.Movie
import com.serj113.presentation.BuildConfig
import com.serj113.presentation.databinding.MovieListItemBinding

class MoviePagingAdapter(
    private val onItemClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MoviePagingAdapter.MovieItemViewHolder>(MovieItemCallback) {

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener { onItemClick.invoke(movie) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieItemViewHolder {
        return MovieItemViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class MovieItemViewHolder(
        private val binding: MovieListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvRate.text = movie.voteAverage.toString()
            Glide
                .with(binding.root)
                .load("${BuildConfig.IMAGE_URL}/${movie.posterPath}")
                .into(binding.ivPoster)
        }
    }
}