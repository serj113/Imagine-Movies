package com.serj113.imaginemovies.ui.list

import androidx.lifecycle.ViewModel
import com.serj113.imaginemovies.domain.interactor.FetchMovieUseCase
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val useCase: FetchMovieUseCase
) : ViewModel() {
    val pagedEntityMovie = useCase.observe().apply {
        useCase.invoke()
    }
}
