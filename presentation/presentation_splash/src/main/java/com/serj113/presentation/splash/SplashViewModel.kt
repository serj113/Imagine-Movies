package com.serj113.presentation.splash

import com.serj113.imaginemovies.base.presentation.BaseViewModel
import com.serj113.imaginemovies.base.presentation.util.Event
import com.serj113.imaginemovies.base.domain.interactor.IsLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: IsLoginUseCase
) : BaseViewModel<SplashViewState>() {

//    private val _viewState = MutableLiveData<com.serj113.base_presentation.Event<SplashViewState>>()
//    val viewState: LiveData<Event<SplashViewState>> = _viewState

//    fun loadInitialData() = viewModelScope.launch {
//        useCase.invoke(Unit).collect {
//            _viewState.value = Event(
//                if (it)SplashViewState.GoToMovieList else SplashViewState.GoToLogin
//            )
//        }
//    }

    fun loadInitialData() {
        _viewState.value = Event(SplashViewState.GoToMovieList)
    }
}
