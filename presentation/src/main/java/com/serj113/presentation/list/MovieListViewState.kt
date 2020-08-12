package com.serj113.presentation.list

import androidx.paging.PagedList
import androidx.paging.PagingData
import com.serj113.domain.entity.Movie

sealed class MovieListViewState {
    data class Success(val data: PagingData<Movie>?) : MovieListViewState()
    object Loading : MovieListViewState()
    data class Error(var error: Throwable) : MovieListViewState()
}