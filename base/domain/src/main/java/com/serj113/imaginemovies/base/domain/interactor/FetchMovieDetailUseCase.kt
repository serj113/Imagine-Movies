package com.serj113.imaginemovies.base.domain.interactor

import com.serj113.imaginemovies.base.domain.Entity
import com.serj113.imaginemovies.base.domain.FlowUseCaseWithArgs
import com.serj113.model.MovieDetail

abstract class FetchMovieDetailUseCase :
    FlowUseCaseWithArgs<FetchMovieDetailUseCase.Args, Entity<MovieDetail>>() {
    data class Args(
        val movieId: Long
    )
}
