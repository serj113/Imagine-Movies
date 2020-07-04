package com.serj113.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.serj113.domain.base.NetworkState

abstract class BaseDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {
    protected val state = MutableLiveData<NetworkState>(NetworkState.LOADING)
    protected var totalPages: Long = 1L
    protected var page: Long = 1L

    fun getState(): LiveData<NetworkState> = state
}