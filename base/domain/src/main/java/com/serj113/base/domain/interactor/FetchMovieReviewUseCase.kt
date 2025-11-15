package com.serj113.base.domain.interactor

import com.serj113.base.domain.Entity
import com.serj113.base.domain.FlowUseCaseWithArgs
import com.serj113.model.ReviewList

abstract class FetchMovieReviewUseCase :
    FlowUseCaseWithArgs<FetchMovieReviewUseCase.Args, Entity<ReviewList>>() {
    data class Args(
        val movieId: Long,
        val page: Long
    )
}
