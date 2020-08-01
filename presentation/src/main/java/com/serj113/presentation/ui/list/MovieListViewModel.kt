package com.serj113.presentation.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.serj113.domain.base.Entity
import com.serj113.domain.base.Entity.Idle
import com.serj113.domain.base.Entity.Success
import com.serj113.domain.base.Entity.Error
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

        postValue(Idle())

        addSource(listMovie) {
            postValue(Success(it))
        }
    }

    val listViewState: LiveData<MovieListViewState> = Transformations.map(entityListMovie) { entity ->
        when (entity) {
            is Error -> MovieListViewState.Error(entity.t)
            is Success -> MovieListViewState.Success(entity.data)
            else -> MovieListViewState.Loading
        }
    }
}