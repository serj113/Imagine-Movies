package com.serj113.presentation.datasource

import androidx.paging.PagingSource
import com.serj113.domain.base.Entity
import com.serj113.domain.entity.Review
import com.serj113.domain.interactor.FetchMovieReviewUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.io.IOException

class ReviewPagingDataSource constructor(
    private val movieId: Long,
    private val useCase: FetchMovieReviewUseCase
) : PagingSource<Long, Review>() {
    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Review> {
        return try {
            val mutableListReview = mutableListOf<Review>()
            val pageKey = params.key ?: 1L
            useCase.invoke(FetchMovieReviewUseCase.Args(movieId, pageKey))
                .onEach { entity ->
                    when (entity) {
                        is Entity.Success -> {
                            mutableListReview.addAll(entity.data)
                        }
                    }
                }
                .collect()

            LoadResult.Page(
                data = mutableListReview,
                prevKey = null,
                nextKey = pageKey + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}