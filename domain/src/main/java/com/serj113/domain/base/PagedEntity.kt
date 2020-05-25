package com.serj113.domain.base

import androidx.paging.PagedList

data class PagedEntity<T: Any>(
    var value: PagedList<T>?,
    var state: NetworkState
)