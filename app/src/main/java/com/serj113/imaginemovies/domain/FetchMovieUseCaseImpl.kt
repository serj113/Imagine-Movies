package com.serj113.imaginemovies.domain

import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieUseCaseImpl @Inject constructor(
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