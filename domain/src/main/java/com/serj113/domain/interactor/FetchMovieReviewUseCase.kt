package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCase
import com.serj113.domain.entity.Review

abstract class FetchMovieReviewUseCase :
    FlowUseCase<FetchMovieReviewUseCase.Args, Entity<List<Review>>>() {
    data class Args(
        val movieId: Long,
        val page: Long
    )
}