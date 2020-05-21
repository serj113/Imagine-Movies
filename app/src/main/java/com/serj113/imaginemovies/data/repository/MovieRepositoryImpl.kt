package com.serj113.imaginemovies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.serj113.imaginemovies.data.api.MovieApi
import com.serj113.imaginemovies.data.factory.MovieFactory
import com.serj113.imaginemovies.domain.base.NetworkState
import com.serj113.imaginemovies.domain.base.PagedEntity
import com.serj113.imaginemovies.domain.entity.Movie
import com.serj113.imaginemovies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val factory: MovieFactory
) : MovieRepository {

    private val pagedListMovie = MediatorLiveData<PagedEntity<Movie>>()

    private val config = PagedList.Config.Builder().apply {
        setEnablePlaceholders(false)
        setPageSize(20)
    }.build()

    override fun fetchMovies(): LiveData<PagedEntity<Movie>> {
        val listMovie = LivePagedListBuilder<Long, Movie>(
            factory,
            config
        ).build()

        factory.getDataSourceState?.let {
            pagedListMovie.addSource(it.invoke()) { networkState ->
                val listMovie = pagedListMovie.value?.value ?: listOf()
                pagedListMovie.postValue(PagedEntity(listMovie, networkState))
            }
        }
        pagedListMovie.addSource(listMovie) {
            val networkState = pagedListMovie.value?.state ?: NetworkState.LOADING
            pagedListMovie.postValue(PagedEntity(it, networkState))
        }

        return pagedListMovie
    }
}