package com.serj113.imaginemovies.base.domain

interface BaseUseCase<out T: Any> {
    fun invoke(): T
}
