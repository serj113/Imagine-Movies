package com.serj113.imaginemovies.data.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.serj113.imaginemovies.data.api.MovieApi
import com.serj113.imaginemovies.data.base.BaseDataSourceFactory
import com.serj113.imaginemovies.data.datasource.PageKeyedMovieDataSource
import com.serj113.imaginemovies.domain.base.NetworkState
import com.serj113.imaginemovies.domain.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFactory @Inject constructor(
    private val movieApi: MovieApi
) : BaseDataSourceFactory<Long, Movie>() {
    override fun create(): DataSource<Long, Movie> {
        return PageKeyedMovieDataSource(movieApi).also {
            dataSource = it
            GlobalScope.launch(Dispatchers.Main) {
                val sourceState = it.getState()
                dataSourceState.removeSource(sourceState)
                dataSourceState.addSource(sourceState) { networkState ->
                    dataSourceState.postValue(networkState)
                }
            }
        }
    }
}