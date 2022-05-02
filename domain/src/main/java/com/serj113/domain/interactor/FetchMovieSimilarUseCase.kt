package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCaseWithArgs
import com.serj113.model.MovieList

abstract class FetchMovieSimilarUseCase :
    FlowUseCaseWithArgs<FetchMovieSimilarUseCase.Args, Entity<MovieList>>() {
    data class Args(
        val movieId: Long,
        val page: Long
    )
}