package com.serj113.domain.base

data class Entity<T: Any>(
    var value: T?,
    var state: NetworkState
)