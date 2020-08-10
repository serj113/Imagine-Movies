package com.serj113.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serj113.domain.entity.Movie
import com.serj113.presentation.BuildConfig

class MovieDetailViewModel @ViewModelInject constructor() : ViewModel() {
    private val movieBackdrop = MutableLiveData<String>()
    private val movieSynopsis = MutableLiveData<String>()

    fun bind(movie: Movie) {
        movieBackdrop.postValue(BuildConfig.IMAGE_URL + movie.backdropPath)
        movieSynopsis.postValue(movie.overview)
    }

    fun getMovieBackdrop(): LiveData<String> = movieBackdrop

    fun getMovieSynopsis(): LiveData<String> = movieSynopsis
}