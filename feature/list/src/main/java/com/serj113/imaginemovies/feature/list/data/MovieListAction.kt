package com.serj113.imaginemovies.feature.list.data

sealed interface MovieListAction {
    object OnTapItem : MovieListAction
    object LoadMore : MovieListAction
}