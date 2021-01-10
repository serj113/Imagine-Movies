package com.serj113.data.repository

import com.serj113.data.api.MovieApi
import com.serj113.data.base.NetworkBoundResource
import com.serj113.data.local.database.MovieDao
import com.serj113.data.local.database.entity.MovieLocal
import com.serj113.data.local.database.entity.toEntity
import com.serj113.domain.base.Entity
import com.serj113.domain.base.Entity.Error
import com.serj113.domain.base.Entity.Success
import com.serj113.domain.base.Entity.Loading
import com.serj113.domain.repository.MovieRepository
import com.serj113.model.MovieDetail
import com.serj113.model.MovieList
import com.serj113.model.ReviewList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieApi: MovieApi
) : MovieRepository {
    override fun fetchMovies(page: Long): Flow<Entity<MovieList>> {
        return object : NetworkBoundResource<MovieList, List<MovieLocal>>() {
            override fun shouldFetch(data: MovieList?): Boolean {
                return data == null || data.results.isEmpty()
            }

            override suspend fun fetchRemote(): Flow<Entity<MovieList>> {
                return flow {
                    val response = movieApi.getDiscoverMovie(page = page)
                    if (response.isSuccessful) {
                        try {
                            emit(Success(response.body()!!))
                        } catch (e: Throwable) {
                            emit(Error(e))
                        }
                    }
                }.flowOn(Dispatchers.IO)
            }

            override suspend fun loadLocal(): Flow<MovieList> {
                val movies = movieDao.getAllMovie()
                return movies.map {
                    it.toEntity()
                }.flowOn(Dispatchers.IO)
            }
        }.getData()
    }

    override fun fetchMovieReviews(movieId: Long, page: Long): Flow<Entity<ReviewList>> {
        return flow {
            emit(Loading)
            val response = movieApi.getMovieReviews(id = movieId, page = page)
            if (response.isSuccessful) {
                try {
                    emit(Success(response.body()!!))
                } catch (e: Throwable) {
                    emit(Error(e))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchMovieDetail(movieId: Long): Flow<Entity<MovieDetail>> {
        return flow {
            emit(Loading)
            val response = movieApi.getMovieDetail(id = movieId)
            if (response.isSuccessful) {
                try {
                    emit(Success(response.body()!!))
                } catch (e: Throwable) {
                    emit(Error(e))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}