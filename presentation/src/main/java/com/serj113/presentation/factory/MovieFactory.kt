package com.serj113.presentation.factory

import androidx.paging.DataSource
import com.serj113.domain.entity.Movie
import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.presentation.base.BaseDataSourceFactory
import com.serj113.presentation.datasource.PageKeyedMovieDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieFactory (
    private val useCase: FetchMovieUseCase
) : BaseDataSourceFactory<Long, Movie>() {
    override fun create(): DataSource<Long, Movie> {
        return PageKeyedMovieDataSource(useCase).also {
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