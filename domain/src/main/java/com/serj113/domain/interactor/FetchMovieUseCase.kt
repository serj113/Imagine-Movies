package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCaseWithArgs
import com.serj113.model.MovieList

abstract class FetchMovieUseCase :
    FlowUseCaseWithArgs<FetchMovieUseCase.Args, Entity<MovieList>>() {
    data class Args(
        val page: Long
    )
}
