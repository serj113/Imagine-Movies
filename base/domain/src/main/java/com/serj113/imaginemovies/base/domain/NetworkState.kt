package com.serj113.imaginemovies.base.domain

sealed class NetworkState {
    object LOADING : NetworkState()
    object SUCCESS : NetworkState()
    data class FAILED(var error: Throwable) : NetworkState()
}