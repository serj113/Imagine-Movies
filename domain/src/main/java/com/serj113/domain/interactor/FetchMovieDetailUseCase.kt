package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCaseWithArgs
import com.serj113.model.MovieDetail

abstract class FetchMovieDetailUseCase :
    FlowUseCaseWithArgs<FetchMovieDetailUseCase.Args, Entity<MovieDetail>>() {
    data class Args(
        val movieId: Long
    )
}
