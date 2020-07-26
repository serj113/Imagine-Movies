package com.serj113.domain.base

sealed class SealedEntity<out T : Any> {
    object Idle : SealedEntity<Nothing>()
    class Success<out T : Any>(data: T) : SealedEntity<T>()
    object Loading : SealedEntity<Nothing>()
    class Error(t: Throwable) : SealedEntity<Nothing>()
}