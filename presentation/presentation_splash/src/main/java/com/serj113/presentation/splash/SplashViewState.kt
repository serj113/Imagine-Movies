package com.serj113.presentation.splash

sealed class SplashViewState {
    object GoToLogin : SplashViewState()
    object GoToMovieList : SplashViewState()
}