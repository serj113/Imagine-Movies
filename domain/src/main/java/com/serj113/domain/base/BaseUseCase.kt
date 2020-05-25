package com.serj113.domain.base

interface BaseUseCase<T: Any> {
    fun invoke(): T
}