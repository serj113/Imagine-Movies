package com.serj113.presentation.ui.list

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.serj113.domain.base.Entity
import com.serj113.domain.base.NetworkState
import com.serj113.domain.base.PagedEntity
import com.serj113.domain.entity.Movie
import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.presentation.factory.MovieFactory
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val sourceFactory: MovieFactory
) : ViewModel() {
    private val config = PagedList.Config.Builder().apply {
        setEnablePlaceholders(false)
        setPageSize(20)
    }.build()

    val pagedEntityMovies = MediatorLiveData<PagedEntity<Movie>>().apply {
        val listMovie = LivePagedListBuilder(
            sourceFactory,
            config
        ).build()

        postValue(PagedEntity(null, NetworkState.LOADING))

        addSource(sourceFactory.dataSourceState) { networkState ->
            postValue(PagedEntity(listMovie.value, networkState))
        }
        addSource(listMovie) {
            val networkState = sourceFactory.dataSourceState.value ?: NetworkState.LOADING
            postValue(PagedEntity(it, networkState))
        }
    }
}