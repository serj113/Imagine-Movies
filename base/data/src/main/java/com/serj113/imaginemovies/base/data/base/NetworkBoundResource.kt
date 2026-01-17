package com.serj113.imaginemovies.base.data.base

import com.serj113.imaginemovies.base.domain.Entity
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType : Any, RequestType : Any> {

    private var result: Flow<Entity<ResultType>> = flow {
        emit(Entity.Loading)
        val localResult = loadLocal().firstOrNull()
        if (shouldFetch(localResult)) {
            emit(fetchRemote().firstOrNull() ?: Entity.Error(Throwable()))
        } else {
            emitAll(
                loadLocal().map {
                    Entity.Success(it)
                }
            )
        }
    }

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun fetchRemote(): Flow<Entity<ResultType>>

    protected abstract suspend fun loadLocal(): Flow<ResultType>

    protected open fun onFetchFailed() {}

    protected open suspend fun saveCallResult(data: RequestType) {}

    fun getData(): Flow<Entity<ResultType>> = result
}
