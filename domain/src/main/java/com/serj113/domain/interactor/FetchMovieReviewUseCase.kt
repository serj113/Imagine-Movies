package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCaseWithArgs
import com.serj113.model.ReviewList

abstract class FetchMovieReviewUseCase :
    FlowUseCaseWithArgs<FetchMovieReviewUseCase.Args, Entity<ReviewList>>() {
    data class Args(
        val movieId: Long,
        val page: Long
    )
}
