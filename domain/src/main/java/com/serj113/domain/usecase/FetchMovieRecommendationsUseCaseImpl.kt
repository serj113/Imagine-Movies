package com.serj113.domain.usecase

import com.serj113.domain.interactor.FetchMovieRecommendationsUseCase
import com.serj113.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieRecommendationsUseCaseImpl @Inject constructor(
    private var movieRepository: MovieRepository
) : FetchMovieRecommendationsUseCase() {
    override fun invoke(
        args: Args
    ) = movieRepository.fetchMovieRecommendations(args.movieId, args.page)
}