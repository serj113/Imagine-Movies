package com.serj113.imaginemovies.ui.list

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.serj113.imaginemovies.domain.base.PagedEntity
import com.serj113.imaginemovies.domain.entity.Movie
import com.serj113.imaginemovies.domain.interactor.FetchMovieUseCase
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val useCase: FetchMovieUseCase
) : ViewModel() {
    val pagedEntityMovie = useCase.observe().apply {
        useCase.invoke()
    }

    override fun onCleared() {
        super.onCleared()
    }
}
