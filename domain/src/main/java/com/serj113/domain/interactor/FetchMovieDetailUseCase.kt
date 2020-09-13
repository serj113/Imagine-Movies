package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCase
import com.serj113.model.MovieDetail

abstract class FetchMovieDetailUseCase :
    FlowUseCase<FetchMovieDetailUseCase.Args, Entity<MovieDetail>>() {
    data class Args(
        val movieId: Long
    )
}