package com.serj113.imaginemovies.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.serj113.domain.entity.Movie

object MovieItemCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
}