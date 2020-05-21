package com.serj113.imaginemovies.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import com.serj113.imaginemovies.domain.base.NetworkState
import com.serj113.imaginemovies.domain.entity.Movie
import com.serj113.imaginemovies.domain.base.PagedEntity
import com.serj113.imaginemovies.domain.interactor.FetchMovieUseCase
import com.serj113.imaginemovies.domain.repository.MovieRepository
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