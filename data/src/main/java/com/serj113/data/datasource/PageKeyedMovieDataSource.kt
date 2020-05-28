package com.serj113.data.datasource

import androidx.paging.PageKeyedDataSource
import com.serj113.data.api.MovieApi
import com.serj113.data.base.BaseDataSource
import com.serj113.data.model.toMovieEntities
import com.serj113.domain.base.NetworkState
import com.serj113.domain.entity.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PageKeyedMovieDataSource constructor(
    private val service: MovieApi
) : BaseDataSource<Long, Movie>() {

    override fun loadInitial(
        params: PageKeyedDataSource.LoadInitialParams<Long>,
        callback: PageKeyedDataSource.LoadInitialCallback<Long, Movie>
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

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Long>, callback: PageKeyedDataSource.LoadCallback<Long, Movie>) {
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

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Long>, callback: PageKeyedDataSource.LoadCallback<Long, Movie>) = Unit
}