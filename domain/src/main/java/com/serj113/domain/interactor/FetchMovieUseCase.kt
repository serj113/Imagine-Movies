package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCase
import com.serj113.model.MovieList

abstract class FetchMovieUseCase : FlowUseCase<FetchMovieUseCase.Args, Entity<MovieList>>() {
    data class Args(
        val page: Long
    )
}