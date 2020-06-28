package com.serj113.data.repository

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.serj113.data.factory.MovieFactory
import com.serj113.domain.base.NetworkState
import com.serj113.domain.base.PagedEntity
import com.serj113.domain.entity.Movie
import com.serj113.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val factory: MovieFactory
) : MovieRepository {

    private val config = PagedList.Config.Builder().apply {
        setEnablePlaceholders(false)
        setPageSize(20)
    }.build()

    override fun fetchMovies(): Flow<PagedEntity<Movie>> {
        factory.finalize()
        val listMovie = LivePagedListBuilder<Long, Movie>(
            factory,
            config
        ).build()

        val pagedListMovie = MediatorLiveData<PagedEntity<Movie>>().apply {
            postValue(PagedEntity(null, NetworkState.LOADING))
        }

        pagedListMovie.addSource(factory.dataSourceState) { networkState ->
            pagedListMovie.postValue(PagedEntity(listMovie.value, networkState))
        }

        pagedListMovie.addSource(listMovie) {
            val networkState = factory.dataSourceState.value ?: NetworkState.LOADING
            pagedListMovie.postValue(PagedEntity(it, networkState))
        }

        return pagedListMovie.asFlow()
    }
}