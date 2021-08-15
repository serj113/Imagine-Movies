package com.serj113.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serj113.domain.base.Entity.Success
import com.serj113.domain.interactor.FetchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: FetchMovieUseCase
) : ViewModel() {
    private var page = 1L

    private val _movieListViewState: MutableLiveData<MovieListViewState> =
        MutableLiveData(MovieListViewState.Loading)
    val movieListViewState: LiveData<MovieListViewState> = _movieListViewState

    fun fetchMovieList() {
        viewModelScope.launch(Dispatchers.Default) {
            useCase
                .invoke(FetchMovieUseCase.Args(page))
                .onEach {
                    when (it) {
                        is Success -> {
                            page += 1
                            _movieListViewState.postValue(
                                MovieListViewState.Success(it.data.results)
                            )
                        }
                    }
                }
                .collect()
        }
    }
}
