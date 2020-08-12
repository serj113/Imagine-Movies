package com.serj113.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.serj113.domain.base.Entity
import com.serj113.domain.entity.Movie
import com.serj113.domain.entity.Review
import com.serj113.domain.interactor.FetchMovieReviewUseCase
import com.serj113.presentation.BuildConfig
import com.serj113.presentation.datasource.ReviewPagingDataSource

class MovieDetailViewModel @ViewModelInject constructor(
    private val useCase: FetchMovieReviewUseCase
) : ViewModel() {
    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false )

    private val movieBackdrop = MutableLiveData<String>()
    private val movieSynopsis = MutableLiveData<String>()
    private var movieId = 0L

    private var entityListReview = MediatorLiveData<Entity<PagingData<Review>>>()

    fun bind(movie: Movie) {
        movieBackdrop.postValue(BuildConfig.IMAGE_URL + movie.backdropPath)
        movieSynopsis.postValue(movie.overview)
        movieId = movie.id.toLong()
        entityListReview = MediatorLiveData<Entity<PagingData<Review>>>().apply {
            val listReview = Pager(
                config = pagingConfig,
                pagingSourceFactory = { ReviewPagingDataSource(movieId, useCase) }
            ).liveData

            postValue(Entity.Idle())

            addSource(listReview) {
                postValue(Entity.Success(it))
            }
        }
    }

    fun getMovieBackdrop(): LiveData<String> = movieBackdrop

    fun getMovieSynopsis(): LiveData<String> = movieSynopsis

    fun getListReview(): LiveData<Entity<PagingData<Review>>> = entityListReview
}