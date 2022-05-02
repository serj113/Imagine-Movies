package com.serj113.domain.usecase

import com.serj113.domain.interactor.FetchMovieSimilarUseCase
import com.serj113.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieSimilarUseCaseImpl @Inject constructor(
    private var movieRepository: MovieRepository
) : FetchMovieSimilarUseCase() {
    override fun invoke(
        args: Args
    ) = movieRepository.fetchMovieSimilar(args.movieId, args.page)
}