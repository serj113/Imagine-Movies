package com.serj113.imaginemovies.base.domain.interactor

import com.serj113.imaginemovies.base.domain.Entity
import com.serj113.imaginemovies.base.domain.FlowUseCaseWithArgs
import com.serj113.imaginemovies.base.model.MovieList

abstract class FetchMovieUseCase :
    FlowUseCaseWithArgs<FetchMovieUseCase.Args, Entity<MovieList>>() {
    data class Args(
        val page: Long
    )
}
