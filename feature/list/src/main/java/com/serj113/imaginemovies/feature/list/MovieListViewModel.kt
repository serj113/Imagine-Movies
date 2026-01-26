package com.serj113.imaginemovies.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serj113.imaginemovies.base.domain.Entity
import com.serj113.imaginemovies.base.domain.interactor.FetchMovieUseCase
import com.serj113.imaginemovies.base.domain.interactor.FetchPopularMovieUseCase
import com.serj113.imaginemovies.base.model.Movie
import com.serj113.imaginemovies.feature.list.data.MovieListAction
import com.serj113.imaginemovies.feature.list.data.MovieListEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: FetchMovieUseCase,
    private val popularMovieUseCase: FetchPopularMovieUseCase
) : ViewModel(), IMovieListViewModel {
    private var page = 2L
    private var movieList = mutableListOf<Movie>()
    private var popularMovieList = mutableListOf<Movie>()

    private val _movieListViewState: MutableLiveData<MovieListViewState> =
        MutableLiveData(MovieListViewState.Loading)
    val movieListViewState: LiveData<MovieListViewState> = _movieListViewState

    private val actions: MutableSharedFlow<MovieListAction> = MutableSharedFlow()

    private val mutableEffect: MutableSharedFlow<MovieListEffect> = MutableSharedFlow()
    val effect: SharedFlow<MovieListEffect> = mutableEffect

    private val mutableStateFlow: MutableStateFlow<com.serj113.imaginemovies.feature.list.data.MovieListViewState> =
        MutableStateFlow(com.serj113.imaginemovies.feature.list.data.MovieListViewState())

    val stateFlow: StateFlow<com.serj113.imaginemovies.feature.list.data.MovieListViewState> =
        mutableStateFlow.asStateFlow()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = com.serj113.imaginemovies.feature.list.data.MovieListViewState(),
            )

    fun fetchMovieList() {
        viewModelScope.launch(Dispatchers.Default) {
            useCase
                .invoke(FetchMovieUseCase.Args(page))
                .onEach {
                    when (it) {
                        is Entity.Success -> {
                            page += 1
                            movieList.addAll(it.data.results)
                            _movieListViewState.postValue(
                                MovieListViewState.Success(movieList, popularMovieList)
                            )
                        }

                        else -> {}
                    }
                }
                .collect()
        }
    }

    fun fetchPopularMovieList() {
        viewModelScope.launch(Dispatchers.Default) {
            popularMovieUseCase
                .invoke()
                .onEach {
                    when (it) {
                        is Entity.Success -> {
                            popularMovieList.addAll(it.data.results)
                            _movieListViewState.postValue(
                                MovieListViewState.Success(movieList, popularMovieList)
                            )
                        }

                        else -> {}
                    }
                }
                .collect()
        }
    }

    override fun onUiAction(action: MovieListAction) {
        actions.tryEmit(action)
    }
}
