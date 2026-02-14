package com.serj113.imaginemovies.feature.detail.data

sealed interface MovieDetailAction {
    object OnBackPressed : MovieDetailAction
}
