package com.serj113.domain.usecase

import com.serj113.domain.interactor.SealedFetchMovieUseCase
import com.serj113.domain.repository.SealedMovieRepository
import javax.inject.Inject

class SealedFetchMovieUseCaseImpl @Inject constructor(
    private val movieRepository: SealedMovieRepository
) : SealedFetchMovieUseCase() {
    override fun invoke(args: Args) = movieRepository.fetchMovies(args.page)
}