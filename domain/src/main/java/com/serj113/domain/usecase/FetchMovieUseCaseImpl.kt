package com.serj113.domain.usecase

import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.domain.repository.MovieRepository

class FetchMovieUseCaseImpl constructor(
    private var movieRepository: MovieRepository
) : FetchMovieUseCase() {

    override fun invoke() {
        val movieList = movieRepository.fetchMovies()

        result.removeSource(movieList)

        result.addSource(movieList) {
            result.postValue(it)
        }
    }
}