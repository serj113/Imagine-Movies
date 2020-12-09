package com.serj113.presentation.login

sealed class LoginViewState {
    object GoToMovieList : LoginViewState()
    data class Error(var error: Throwable) : LoginViewState()
}