package com.serj113.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.serj113.domain.interactor.FetchMovieUseCase
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val useCase: FetchMovieUseCase
) : ViewModel() {
    val pageEntityMovies = useCase.invoke().asLiveData(viewModelScope.coroutineContext)
}