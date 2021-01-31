package com.serj113.presentation.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serj113.common.presentation.base.BaseViewModel
import com.serj113.common.presentation.util.Event
import com.serj113.domain.interactor.IsLoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
    private val useCase: IsLoginUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<Event<SplashViewState>>()
    val viewState: LiveData<Event<SplashViewState>> = _viewState

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