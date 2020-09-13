package com.serj113.domain.base

sealed class Entity<out T : Any> {
    object Idle : Entity<Nothing>()
    object Loading : Entity<Nothing>()
    data class Success<out T : Any>(val data: T) : Entity<T>()
    data class Error(val t: Throwable) : Entity<Nothing>()
}