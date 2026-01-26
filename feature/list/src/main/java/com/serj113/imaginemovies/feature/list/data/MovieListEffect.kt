package com.serj113.imaginemovies.feature.list.data

sealed interface MovieListEffect {
    object ShowLoading : MovieListEffect
    data class ShowError(val message: String) : MovieListEffect
}