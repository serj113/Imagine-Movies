package com.serj113.data.repository

import com.serj113.data.api.MovieApi
import com.serj113.data.model.toMovieEntities
import com.serj113.domain.base.SealedEntity
import com.serj113.domain.entity.Movie
import com.serj113.domain.repository.SealedMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SealedMovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : SealedMovieRepository {
    override fun fetchMovies(page: Long): Flow<SealedEntity<List<Movie>>> {
        return flow {
            emit(SealedEntity.Loading<List<Movie>>())
            val movies: List<Movie> = movieApi.getDiscoverMovie(page = page)
                .results.toMovieEntities()
            emit(SealedEntity.Success(movies))
        }.flowOn(Dispatchers.IO)
    }
}