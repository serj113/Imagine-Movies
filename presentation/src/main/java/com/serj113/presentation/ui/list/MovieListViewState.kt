package com.serj113.presentation.ui.list

import androidx.paging.PagedList
import com.serj113.domain.entity.Movie

sealed class MovieListViewState {
    data class Success(val data: PagedList<Movie>?) : MovieListViewState()
    object Loading : MovieListViewState()
    data class Error(var error: Throwable) : MovieListViewState()
}