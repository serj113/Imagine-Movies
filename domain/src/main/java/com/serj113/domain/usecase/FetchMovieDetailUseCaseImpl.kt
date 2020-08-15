package com.serj113.domain.usecase

import com.serj113.domain.interactor.FetchMovieDetailUseCase
import com.serj113.domain.repository.MovieRepository
import javax.inject.Inject

internal class FetchMovieDetailUseCaseImpl @Inject constructor(
    private var movieRepository: MovieRepository
) : FetchMovieDetailUseCase() {
    override fun invoke(args: Args) = movieRepository.fetchMovieDetail(args.movieId)
}