package com.serj113.data.repository

import com.serj113.data.api.MovieApi
import com.serj113.data.model.toMovieEntities
import com.serj113.domain.base.Entity
import com.serj113.domain.base.NetworkState
import com.serj113.domain.entity.Movie
import com.serj113.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override fun fetchMovies(page: Long): Flow<Entity<List<Movie>>> {
        return flow {
            emit(Entity(listOf(), NetworkState.LOADING))
            val movies: List<Movie> = movieApi.getDiscoverMovie(page = page)
                .results.toMovieEntities()
            emit(Entity(movies, NetworkState.SUCCESS))
        }.flowOn(Dispatchers.IO)
    }
}