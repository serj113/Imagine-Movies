package com.serj113.imaginemovies.base.domain.usecase

import com.serj113.imaginemovies.base.domain.Entity
import com.serj113.imaginemovies.base.domain.interactor.FetchPopularMovieUseCase
import com.serj113.imaginemovies.base.domain.repository.MovieRepository
import com.serj113.imaginemovies.base.model.MovieList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPopularMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : FetchPopularMovieUseCase() {
    override fun invoke(): Flow<Entity<MovieList>> = movieRepository.fetchPopularMovies(1)
}