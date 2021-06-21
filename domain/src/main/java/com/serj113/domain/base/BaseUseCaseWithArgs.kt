package com.serj113.domain.base

interface BaseUseCaseWithArgs<A, out T: Any> {
    fun invoke(args: A): T
}
