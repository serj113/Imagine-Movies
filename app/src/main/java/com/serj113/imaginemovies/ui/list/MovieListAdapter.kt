package com.serj113.imaginemovies.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serj113.imaginemovies.BuildConfig
import com.serj113.imaginemovies.databinding.MovieListItemBinding
import com.serj113.imaginemovies.domain.entity.Movie

class MovieListAdapter(
    private val onItemClick: (Movie) -> Unit
) : PagedListAdapter<Movie, MovieListAdapter.MovieItemViewHolder>(MovieItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener { onItemClick.invoke(movie) }
        }
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