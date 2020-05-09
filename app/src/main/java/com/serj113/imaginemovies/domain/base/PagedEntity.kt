package com.serj113.imaginemovies.domain.base

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class PagedEntity<T: Any>(
    var value: LiveData<PagedList<T>>,
    var state: LiveData<NetworkState>,
    var page: LiveData<Int>,
    var total: LiveData<Int>,
    var hasNext: LiveData<Boolean>
)