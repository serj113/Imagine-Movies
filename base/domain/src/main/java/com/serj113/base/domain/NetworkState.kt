package com.serj113.base.domain

sealed class NetworkState {
    object LOADING : NetworkState()
    object SUCCESS : NetworkState()
    data class FAILED(var error: Throwable) : NetworkState()
}