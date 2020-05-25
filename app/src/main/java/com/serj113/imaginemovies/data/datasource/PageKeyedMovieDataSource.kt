package com.serj113.imaginemovies.data.datasource

import com.serj113.domain.base.NetworkState
import com.serj113.domain.entity.Movie
import com.serj113.imaginemovies.data.api.MovieApi
import com.serj113.imaginemovies.data.base.BaseDataSource
import com.serj113.imaginemovies.data.model.toMovieEntities
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PageKeyedMovieDataSource constructor(
    private val service: MovieApi
) : BaseDataSource<Long, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Movie>
    ) {
        disposables.add(
            service.getDiscoverMovie(page = 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    state.postValue(NetworkState.LOADING)
                }
                .doOnSuccess {
                    totalPages = it.totalPages.toLong()
                    state.postValue(NetworkState.SUCCESS)
                    callback.onResult(it.results.toMovieEntities(), null, it.page.toLong().inc())
                }
                .doOnError {
                    state.postValue(NetworkState.FAILED(it))
                }
                .subscribe()
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        if (params.key > totalPages) return
        disposables.add(
            service.getDiscoverMovie(page = params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    state.postValue(NetworkState.LOADING)
                }
                .doOnSuccess {
                    state.postValue(NetworkState.SUCCESS)
                    callback.onResult(it.results.toMovieEntities(), it.page.toLong().inc())
                }
                .doOnError {
                    state.postValue(NetworkState.FAILED(it))
                }
                .subscribe()
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) = Unit
}