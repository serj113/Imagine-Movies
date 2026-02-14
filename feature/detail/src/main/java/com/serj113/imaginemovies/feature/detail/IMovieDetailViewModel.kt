package com.serj113.imaginemovies.feature.detail

import com.serj113.imaginemovies.feature.detail.data.MovieDetailAction

interface IMovieDetailViewModel {
    fun onUiAction(action: MovieDetailAction)
}
