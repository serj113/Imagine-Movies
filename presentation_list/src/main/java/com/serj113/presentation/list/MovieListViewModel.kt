package com.serj113.presentation.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.*
import com.serj113.domain.base.Entity
import com.serj113.domain.base.Entity.Idle
import com.serj113.domain.base.Entity.Success
import com.serj113.domain.base.Entity.Error
import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.model.Movie

class MovieListViewModel @ViewModelInject constructor(
    private val useCase: FetchMovieUseCase
) : ViewModel() {
    private val config =  PagingConfig(pageSize = 20, enablePlaceholders = false)

    private val entityListMovie = MediatorLiveData<Entity<PagingData<Movie>>>().apply {
        val listMovie = Pager(
            config = config,
            pagingSourceFactory = {
                MoviePagingDataSource(
                    useCase
                )
            }
        ).liveData.cachedIn(viewModelScope)

        postValue(Idle)

        addSource(listMovie) {
            postValue(Success(it))
        }
    }

    val listViewState: LiveData<MovieListViewState> = Transformations.map(entityListMovie) { entity ->
        when (entity) {
            is Error -> MovieListViewState.Error(
                entity.t
            )
            is Success -> MovieListViewState.Success(
                entity.data
            )
            else -> MovieListViewState.Loading
        }
    }
}