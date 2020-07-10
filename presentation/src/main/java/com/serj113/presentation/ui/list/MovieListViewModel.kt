package com.serj113.presentation.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.serj113.domain.base.Entity
import com.serj113.domain.base.NetworkState
import com.serj113.domain.entity.Movie
import com.serj113.presentation.factory.MovieFactory

class MovieListViewModel @ViewModelInject constructor(
    private val sourceFactory: MovieFactory
) : ViewModel() {
    private val config = PagedList.Config.Builder().apply {
        setEnablePlaceholders(false)
        setPageSize(20)
    }.build()

    private val entityListMovie = MediatorLiveData<Entity<PagedList<Movie>>>().apply {
        val listMovie = LivePagedListBuilder(
            sourceFactory,
            config
        ).build()

        postValue(Entity(null, NetworkState.LOADING))

        addSource(sourceFactory.dataSourceState) { networkState ->
            postValue(Entity(listMovie.value, networkState))
        }
        addSource(listMovie) {
            val networkState = sourceFactory.dataSourceState.value ?: NetworkState.LOADING
            postValue(Entity(it, networkState))
        }
    }

    val listViewState: LiveData<MovieListViewState> = Transformations.map(entityListMovie) {
        when (it.state) {
            is NetworkState.FAILED -> MovieListViewState.Error((it.state as NetworkState.FAILED).error)
            is NetworkState.SUCCESS -> MovieListViewState.Success(it.value)
            else -> MovieListViewState.Loading
        }
    }
}