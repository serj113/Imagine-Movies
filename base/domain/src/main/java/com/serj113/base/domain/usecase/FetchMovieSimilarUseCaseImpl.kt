package com.serj113.base.domain.usecase

import com.serj113.base.domain.interactor.FetchMovieSimilarUseCase
import com.serj113.base.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieSimilarUseCaseImpl @Inject constructor(
    private var movieRepository: MovieRepository
) : FetchMovieSimilarUseCase() {
    override fun invoke(
        args: Args
    ) = movieRepository.fetchMovieSimilar(args.movieId, args.page)
}