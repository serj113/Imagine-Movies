package com.serj113.data.factory

import androidx.paging.DataSource
import com.serj113.data.api.MovieApi
import com.serj113.data.base.BaseDataSourceFactory
import com.serj113.data.datasource.PageKeyedMovieDataSource
import com.serj113.domain.entity.Movie
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