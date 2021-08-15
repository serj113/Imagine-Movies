package com.serj113.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serj113.model.Movie
import com.serj113.presentation.list.databinding.MovieListItemBinding

class MovieRecyclerViewAdapter(
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {
    private var mValues: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener { onItemClick.invoke(movie) }
        }
    }

    override fun getItemCount(): Int = mValues.size

    fun addItems(items: List<Movie>) {
        mValues.addAll(items)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = mValues[position]

    inner class ViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
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
