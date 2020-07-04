package com.serj113.domain.base

interface BaseUseCase<A, out T: Any> {
    fun invoke(args: A): T
}