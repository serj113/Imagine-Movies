package com.serj113.presentation.detail.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingState
import com.serj113.domain.base.Entity.Success
import com.serj113.domain.interactor.FetchMovieReviewUseCase
import com.serj113.model.Review
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
            var totalPages = 0
            useCase.invoke(FetchMovieReviewUseCase.Args(movieId, pageKey))
                .onEach { entity ->
                    when (entity) {
                        is Success -> {
                            totalPages = entity.data.totalPages
                            mutableListReview.addAll(entity.data.results)
                        }
                    }
                }
                .collect()

            val nextKey = if (pageKey + 1 > totalPages) null else pageKey + 1
            Page(
                data = mutableListReview,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Long, Review>): Long? {
        return state.anchorPosition?.toLong()
    }
}
