package com.serj113.presentation.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingSource.LoadResult.Error
import com.serj113.domain.base.Entity
import com.serj113.domain.entity.Movie
import com.serj113.domain.interactor.FetchMovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.io.IOException

class MoviePagingDataSource constructor(
    private val useCase: FetchMovieUseCase
) : PagingSource<Long, Movie>() {
    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Movie> {
        return try {
            val mutableListMovies = mutableListOf<Movie>()
            val pageKey = params.key ?: 1L
            useCase.invoke(FetchMovieUseCase.Args(pageKey))
                .onEach { entity ->
                    when (entity) {
                        is Entity.Success -> {
                            mutableListMovies.addAll(entity.data)
                        }
                    }
                }
                .collect()

            Page(
                data = mutableListMovies,
                prevKey = null,
                nextKey = pageKey + 1
            )
        } catch (exception: IOException) {
            return Error(exception)
        }
    }
}