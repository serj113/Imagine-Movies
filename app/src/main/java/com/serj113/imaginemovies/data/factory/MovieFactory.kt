package com.serj113.imaginemovies.data.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.serj113.imaginemovies.data.api.MovieApi
import com.serj113.imaginemovies.data.datasource.PageKeyedMovieDataSource
import com.serj113.imaginemovies.domain.base.NetworkState
import com.serj113.imaginemovies.domain.entity.Movie
import javax.inject.Inject

class MovieFactory @Inject constructor(private val movieApi: MovieApi) : DataSource.Factory<Long, Movie>() {

    var getDataSourceState: (() -> LiveData<NetworkState>)? = null

    override fun create(): DataSource<Long, Movie> {
        return PageKeyedMovieDataSource(movieApi).apply {
            getDataSourceState = this::getState
        }
    }
}