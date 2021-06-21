package com.serj113.domain.base

interface BaseUseCase<out T: Any> {
    fun invoke(): T
}
