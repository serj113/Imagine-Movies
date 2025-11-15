package com.serj113.base.domain.usecase

import com.serj113.base.domain.interactor.FetchMovieRecommendationsUseCase
import com.serj113.base.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieRecommendationsUseCaseImpl @Inject constructor(
    private var movieRepository: MovieRepository
) : FetchMovieRecommendationsUseCase() {
    override fun invoke(
        args: Args
    ) = movieRepository.fetchMovieRecommendations(args.movieId, args.page)
}