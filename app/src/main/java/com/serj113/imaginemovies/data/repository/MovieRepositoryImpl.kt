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

    private val pagedListMovie = MediatorLiveData<PagedEntity<Movie>>().apply {
        postValue(PagedEntity(null, NetworkState.LOADING))
    }

    private val config = PagedList.Config.Builder().apply {
        setEnablePlaceholders(false)
        setPageSize(20)
    }.build()

    override fun fetchMovies(): LiveData<PagedEntity<Movie>> {
        val listMovie = LivePagedListBuilder<Long, Movie>(
            factory,
            config
        ).build()

        pagedListMovie.removeSource(factory.dataSourceState)
        pagedListMovie.removeSource(listMovie)
        pagedListMovie.value = null

        pagedListMovie.addSource(factory.dataSourceState) { networkState ->
            pagedListMovie.postValue(PagedEntity(listMovie.value, networkState))
        }

        pagedListMovie.addSource(listMovie) {
            val networkState = factory.dataSourceState.value ?: NetworkState.LOADING
            pagedListMovie.postValue(PagedEntity(it, networkState))
        }

        return pagedListMovie
    }
}