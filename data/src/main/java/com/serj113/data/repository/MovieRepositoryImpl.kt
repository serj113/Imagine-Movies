package com.serj113.data.repository

import com.serj113.data.api.MovieApi
import com.serj113.data.model.toMovieDetailEntity
import com.serj113.data.model.toMovieEntities
import com.serj113.data.model.toReviewEntities
import com.serj113.data.model.toReviewEntity
import com.serj113.domain.base.Entity
import com.serj113.domain.base.Entity.Success
import com.serj113.domain.base.Entity.Loading
import com.serj113.domain.entity.Movie
import com.serj113.domain.entity.MovieDetail
import com.serj113.domain.entity.MovieReview
import com.serj113.domain.entity.Review
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
            emit(Loading)
            val movies: List<Movie> = movieApi.getDiscoverMovie(page = page)
                .results.toMovieEntities()
            emit(Success(movies))
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchMovieReviews(movieId: Long, page: Long): Flow<Entity<MovieReview>> {
        return flow {
            emit(Loading)
            val reviews: MovieReview = movieApi.getMovieReviews(id = movieId, page = page)
                .toReviewEntity()
            emit(Success(reviews))
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchMovieDetail(movieId: Long): Flow<Entity<MovieDetail>> {
        return flow {
            emit(Loading)
            val response = movieApi.getMovieDetail(id = movieId)
            val movieDetail: MovieDetail = response.toMovieDetailEntity()
            emit(Success(movieDetail))
        }.flowOn(Dispatchers.IO)
    }
}