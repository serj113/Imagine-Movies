package com.serj113.imaginemovies.base.domain

interface BaseUseCaseWithArgs<A, out T: Any> {
    fun invoke(args: A): T
}
