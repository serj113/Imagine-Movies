package com.serj113.domain.base

sealed class Entity<out T : Any> {
    class Idle<out T : Any> : Entity<T>()
    class Success<out T : Any>(val data: T) : Entity<T>()
    class Loading<out T : Any> : Entity<T>()
    class Error<out T : Any>(val t: Throwable) : Entity<T>()
}