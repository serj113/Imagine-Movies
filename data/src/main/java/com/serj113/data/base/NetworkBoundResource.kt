package com.serj113.data.base

import com.serj113.domain.base.Entity
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType : Any, RequestType : Any> {

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun fetchRemote(): Flow<Entity<ResultType>>

    protected abstract fun loadLocal(): Flow<ResultType>

    fun getData(): Flow<Entity<ResultType>> = flow {
        emit(Entity.Loading)
        val localResult = loadLocal().firstOrNull()
        if (shouldFetch(localResult)) {
            emit(fetchRemote().first())
        } else {
            emitAll(
                loadLocal().map {
                    Entity.Success(it)
                }
            )
        }
    }
}