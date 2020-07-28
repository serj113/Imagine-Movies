package com.serj113.domain.base

sealed class SealedEntity<out T : Any> {
    class Idle<out T : Any> : SealedEntity<T>()
    class Success<out T : Any>(data: T) : SealedEntity<T>()
    class Loading<out T : Any> : SealedEntity<T>()
    class Error<out T : Any>(t: Throwable) : SealedEntity<T>()
}