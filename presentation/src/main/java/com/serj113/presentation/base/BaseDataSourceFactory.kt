package com.serj113.presentation.base

import androidx.lifecycle.MediatorLiveData
import androidx.paging.DataSource
import com.serj113.domain.base.NetworkState

abstract class BaseDataSourceFactory<Key, Value> : DataSource.Factory<Key, Value>() {
    var dataSourceState = MediatorLiveData<NetworkState>()
    protected lateinit var dataSource: BaseDataSource<Key, Value>
}