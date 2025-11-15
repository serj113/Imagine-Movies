package com.serj113.base.domain

interface BaseUseCase<out T: Any> {
    fun invoke(): T
}
