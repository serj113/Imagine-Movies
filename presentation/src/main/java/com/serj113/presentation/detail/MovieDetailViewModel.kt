package com.serj113.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.serj113.domain.base.Entity
import com.serj113.domain.entity.Cast
import com.serj113.domain.entity.Movie
import com.serj113.domain.entity.MovieDetail
import com.serj113.domain.entity.Review
import com.serj113.domain.interactor.FetchMovieDetailUseCase
import com.serj113.domain.interactor.FetchMovieReviewUseCase
import com.serj113.presentation.BuildConfig
import com.serj113.presentation.datasource.ReviewPagingDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private val fetchMoviewReviewUseCase: FetchMovieReviewUseCase,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase
) : ViewModel() {
    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false )

    private val movieBackdrop = MutableLiveData<String>()
    private val movieSynopsis = MutableLiveData<String>()
    private var movieId = 0L

    private var listCast = MediatorLiveData<List<Cast>>()
    private var entityListReview = MediatorLiveData<Entity<PagingData<Review>>>()

    private fun fetchCast(movieId: Long) {
        viewModelScope.launch {
            val movieDetail = fetchMovieDetailUseCase
                .invoke(FetchMovieDetailUseCase.Args(movieId))
                .asLiveData()
            launch(Dispatchers.Main) {
                listCast = MediatorLiveData<List<Cast>>().apply {
                    addSource(movieDetail) {
                        when (it) {
                            is Entity.Success -> {
                                postValue(it.data.credits.cast)
                            }
                        }
                    }
                }
            }
        }
    }

    fun bind(movie: Movie) {
        movieBackdrop.postValue(BuildConfig.IMAGE_URL + movie.backdropPath)
        movieSynopsis.postValue(movie.overview)
        movieId = movie.id.toLong()
        fetchCast(movieId)
        entityListReview = MediatorLiveData<Entity<PagingData<Review>>>().apply {
            val listReview = Pager(
                config = pagingConfig,
                pagingSourceFactory = { ReviewPagingDataSource(movieId, fetchMoviewReviewUseCase) }
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

    fun getListCast(): LiveData<List<Cast>> = listCast
}