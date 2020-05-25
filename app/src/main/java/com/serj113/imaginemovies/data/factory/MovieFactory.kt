package com.serj113.imaginemovies.data.factory

import androidx.paging.DataSource
import com.serj113.domain.entity.Movie
import com.serj113.imaginemovies.data.api.MovieApi
import com.serj113.imaginemovies.data.base.BaseDataSourceFactory
import com.serj113.imaginemovies.data.datasource.PageKeyedMovieDataSource
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