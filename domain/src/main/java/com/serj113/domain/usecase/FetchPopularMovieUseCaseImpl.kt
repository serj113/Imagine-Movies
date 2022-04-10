package com.serj113.domain.usecase

import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.FetchPopularMovieUseCase
import com.serj113.domain.repository.MovieRepository
import com.serj113.model.MovieList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPopularMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : FetchPopularMovieUseCase() {
    override fun invoke(): Flow<Entity<MovieList>> = movieRepository.fetchPopularMovies(1)
}