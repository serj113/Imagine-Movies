package com.serj113.imaginemovies.base.domain.interactor

import com.serj113.imaginemovies.base.domain.Entity
import com.serj113.imaginemovies.base.domain.FlowUseCaseWithArgs
import com.serj113.model.MovieList

abstract class FetchMovieRecommendationsUseCase :
    FlowUseCaseWithArgs<FetchMovieRecommendationsUseCase.Args, Entity<MovieList>>() {
    data class Args(
        val movieId: Long,
        val page: Long
    )
}