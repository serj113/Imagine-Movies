package com.serj113.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serj113.domain.entity.Movie
import com.serj113.presentation.BuildConfig
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor() : ViewModel() {
    private val movieBackdrop = MutableLiveData<String>()

    fun bind(movie: Movie) {
        movieBackdrop.postValue(BuildConfig.IMAGE_URL + movie.backdropPath)
    }

    fun getMovieBackdrop(): LiveData<String> = movieBackdrop
}