package com.serj113.imaginemovies.base.domain.usecase

import com.serj113.imaginemovies.base.domain.interactor.FetchMovieReviewUseCase
import com.serj113.imaginemovies.base.domain.repository.MovieRepository
import javax.inject.Inject

internal class FetchMovieReviewUseCaseImpl @Inject constructor(
    private var movieRepository: MovieRepository
) : FetchMovieReviewUseCase() {
    override fun invoke(args: Args) = movieRepository.fetchMovieReviews(args.movieId, args.page)
}