package com.serj113.domain.base

import androidx.lifecycle.MediatorLiveData

abstract class MediatorUseCase<T: Any> : BaseUseCase<Unit> {
    val result = MediatorLiveData<T>()

    fun observe() = result

    fun clearResult() {
        result.value = null
    }
}