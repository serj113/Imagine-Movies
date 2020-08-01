package com.serj113.presentation.datasource

import com.serj113.domain.base.NetworkState
import com.serj113.domain.base.SealedEntity.Success
import com.serj113.domain.base.SealedEntity.Loading
import com.serj113.domain.entity.Movie
import com.serj113.domain.interactor.SealedFetchMovieUseCase
import com.serj113.presentation.base.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SealedPageKeyedMovieDataSource constructor(
    private val useCase: SealedFetchMovieUseCase
) : BaseDataSource<Long, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Movie>
    ) {
        val mutableListMovies = mutableListOf<Movie>()
        GlobalScope.launch(Dispatchers.Default) {
            useCase.invoke(SealedFetchMovieUseCase.Args(page))
                .onEach { entity ->
                    when (entity) {
                        is Success -> {
                            mutableListMovies.addAll(entity.data)
                        }
                        is Loading -> {
                            state.postValue(NetworkState.LOADING)
                        }
                    }
                }
                .onCompletion { flowCollector ->
                    if (flowCollector?.cause == null) {
                        state.postValue(NetworkState.SUCCESS)
                        callback.onResult(mutableListMovies, null, page)
                    }
                }
                .collect()
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        val mutableListMovies = mutableListOf<Movie>()
        page += 1
        GlobalScope.launch(Dispatchers.Default) {
            useCase.invoke(SealedFetchMovieUseCase.Args(page))
                .onEach { entity ->
                    when (entity) {
                        is Success -> {
                            mutableListMovies.addAll(entity.data)
                        }
                        is Loading -> {
                            state.postValue(NetworkState.LOADING)
                        }
                    }
                }
                .onCompletion { flowCollector ->
                    if (flowCollector?.cause == null) {
                        state.postValue(NetworkState.SUCCESS)
                        callback.onResult(mutableListMovies, page)
                    }
                }
                .collect()
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) = Unit
}