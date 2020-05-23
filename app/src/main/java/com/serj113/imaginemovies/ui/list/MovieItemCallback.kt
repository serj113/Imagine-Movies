package com.serj113.imaginemovies.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.serj113.imaginemovies.domain.entity.Movie

object MovieItemCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}