package com.serj113.imaginemovies.feature.splash

sealed class SplashViewState {
    object GoToLogin : SplashViewState()
    object GoToMovieList : SplashViewState()
}