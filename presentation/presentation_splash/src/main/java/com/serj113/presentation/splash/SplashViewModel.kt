package com.serj113.presentation.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serj113.base_presentation.BaseViewModel
import com.serj113.base_presentation.util.Event
import com.serj113.domain.interactor.IsLoginUseCase

class SplashViewModel @ViewModelInject constructor(
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