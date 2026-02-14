package com.serj113.imaginemovies.feature.detail.data

sealed interface MovieDetailEffect {
    object GoBack : MovieDetailEffect
}
