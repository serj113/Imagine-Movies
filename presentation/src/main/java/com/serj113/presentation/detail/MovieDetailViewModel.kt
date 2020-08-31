package com.serj113.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.*
import com.serj113.domain.base.Entity
import com.serj113.domain.entity.Cast
import com.serj113.domain.entity.Movie
import com.serj113.domain.entity.Review
import com.serj113.domain.interactor.FetchMovieDetailUseCase
import com.serj113.domain.interactor.FetchMovieReviewUseCase
import com.serj113.presentation.BuildConfig
import com.serj113.presentation.datasource.ReviewPagingDataSource
import com.serj113.presentation.util.DateUtils
import com.serj113.presentation.util.NumberUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private val fetchMoviewReviewUseCase: FetchMovieReviewUseCase,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase
) : ViewModel() {
    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false )

    private val movieBackdrop = MutableLiveData<String>()
    private val movieSynopsis = MutableLiveData<String>()
    private val movieRating = MutableLiveData<String>()
    private val movieVoteCount = MutableLiveData<String>()
    private val movieOriginalTitle = MutableLiveData<String>()
    private val movieReleaseDate = MutableLiveData<String>()
    private val movieStatus = MutableLiveData<String>()
    private val movieBudget = MutableLiveData<String>()
    private val movieRevenue = MutableLiveData<String>()
    private var movieId = 0L

    private var listCast = MediatorLiveData<List<Cast>>()
    private var entityListReview = MediatorLiveData<Entity<PagingData<Review>>>()

    private fun fetchCast(movieId: Long) {
        viewModelScope.launch(Dispatchers.Default) {
            fetchMovieDetailUseCase
                .invoke(FetchMovieDetailUseCase.Args(movieId))
                .onEach {
                    when (it) {
                        is Entity.Success -> {
                            listCast.postValue(it.data.credits.cast)
                            movieStatus.postValue(it.data.status)
                            movieBudget.postValue(NumberUtils.formatCurrency(it.data.budget))
                            movieRevenue.postValue(NumberUtils.formatCurrency(it.data.revenue))
                        }
                    }
                }
                .collect()
        }
    }

    fun bind(movie: Movie) {
        movieBackdrop.postValue(BuildConfig.IMAGE_URL + movie.backdropPath)
        movieSynopsis.postValue(movie.overview)
        movieRating.postValue(movie.voteAverage.toString())
        movieVoteCount.postValue(NumberUtils.formatNumber(movie.voteCount))
        movieOriginalTitle.postValue(movie.originalTitle)
        movieReleaseDate.postValue(DateUtils.formatDate(movie.releaseDate))
        movieId = movie.id.toLong()
        fetchCast(movieId)
        entityListReview.apply {
            val listReview = Pager(
                config = pagingConfig,
                pagingSourceFactory = { ReviewPagingDataSource(movieId, fetchMoviewReviewUseCase) }
            ).liveData.cachedIn(viewModelScope)

            postValue(Entity.Idle())

            addSource(listReview) {
                postValue(Entity.Success(it))
            }
        }
    }

    fun getMovieBackdrop(): LiveData<String> = movieBackdrop

    fun getMovieSynopsis(): LiveData<String> = movieSynopsis

    fun getMovieRating(): LiveData<String> = movieRating

    fun getMovieVoteCount(): LiveData<String> = movieVoteCount

    fun getMovieOriginalTitle(): LiveData<String> = movieOriginalTitle

    fun getMovieReleaseDate(): LiveData<String> = movieReleaseDate

    fun getMovieStatus(): LiveData<String> = movieStatus

    fun getMovieBudget(): LiveData<String> = movieBudget

    fun getMovieRevenue(): LiveData<String> = movieRevenue

    fun getListReview(): LiveData<Entity<PagingData<Review>>> = entityListReview

    fun getListCast(): LiveData<List<Cast>> = listCast
}