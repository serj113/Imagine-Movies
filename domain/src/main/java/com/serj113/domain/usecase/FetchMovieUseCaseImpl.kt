package com.serj113.domain.usecase

import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieUseCaseImpl @Inject constructor(
    private var movieRepository: MovieRepository
) : FetchMovieUseCase() {

    override fun invoke() = movieRepository.fetchMovies()
}