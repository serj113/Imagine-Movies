package com.serj113.imaginemovies.feature.login

sealed class LoginViewState {
    object GoToMovieList : LoginViewState()
    data class Error(var error: Throwable) : LoginViewState()
}