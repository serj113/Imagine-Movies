package com.serj113.domain.usecase

import com.serj113.domain.interactor.FetchMovieReviewUseCase
import com.serj113.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieReviewUseCaseImpl @Inject constructor(
    private var movieRespository: MovieRepository
) : FetchMovieReviewUseCase() {
    override fun invoke(args: Args) = movieRespository.fetchMovieReviews(args.movieId, args.page)
}