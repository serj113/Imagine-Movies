package com.serj113.presentation.factory

import androidx.paging.DataSource
import com.serj113.domain.entity.Movie
import com.serj113.domain.interactor.SealedFetchMovieUseCase
import com.serj113.presentation.base.BaseDataSourceFactory
import com.serj113.presentation.datasource.SealedPageKeyedMovieDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SealedMovieFactory (
    private val useCase: SealedFetchMovieUseCase
) : BaseDataSourceFactory<Long, Movie>() {
    override fun create(): DataSource<Long, Movie> {
        return SealedPageKeyedMovieDataSource(useCase).also {
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