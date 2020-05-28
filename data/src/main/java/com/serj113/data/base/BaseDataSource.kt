package com.serj113.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.serj113.domain.base.NetworkState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {
    protected val state = MutableLiveData<NetworkState>(NetworkState.LOADING)
    protected val disposables: CompositeDisposable = CompositeDisposable()
    protected var totalPages: Long = 1L

    fun getState(): LiveData<NetworkState> = state

    fun finalize() {
        disposables.clear()
    }
}