package com.serj113.domain.interactor

import com.serj113.domain.base.FlowUseCase
import com.serj113.domain.base.SealedEntity
import com.serj113.domain.entity.Movie

abstract class SealedFetchMovieUseCase : FlowUseCase<SealedFetchMovieUseCase.Args, SealedEntity<List<Movie>>>() {
    data class Args(
        val page: Long
    )
}