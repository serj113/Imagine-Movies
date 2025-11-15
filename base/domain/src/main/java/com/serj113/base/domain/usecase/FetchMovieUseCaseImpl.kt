package com.serj113.base.domain.usecase

import com.serj113.base.domain.interactor.FetchMovieUseCase
import com.serj113.base.domain.repository.MovieRepository
import javax.inject.Inject

internal class FetchMovieUseCaseImpl @Inject constructor(
    private var movieRepository: MovieRepository
) : FetchMovieUseCase() {
    override fun invoke(args: Args) = movieRepository.fetchMovies(args.page)
}