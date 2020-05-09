package com.serj113.imaginemovies.domain.base

interface BaseUseCase<T: Any> {
    fun invoke(): T
}