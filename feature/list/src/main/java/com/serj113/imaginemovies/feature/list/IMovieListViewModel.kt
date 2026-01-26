package com.serj113.imaginemovies.feature.list

import com.serj113.imaginemovies.feature.list.data.MovieListAction

interface IMovieListViewModel {
    fun onUiAction(action: MovieListAction)
}