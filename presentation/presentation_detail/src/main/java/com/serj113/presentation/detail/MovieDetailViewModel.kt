package com.serj113.presentation.detail

import android.util.Log
import androidx.lifecycle.*
import com.serj113.common.presentation.util.DateUtils
import com.serj113.common.presentation.util.NumberUtils
import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.FetchMovieDetailUseCase
import com.serj113.domain.interactor.FetchMovieRecommendationsUseCase
import com.serj113.domain.interactor.FetchMovieReviewUseCase
import com.serj113.domain.interactor.FetchMovieSimilarUseCase
import com.serj113.imaginemovies.presentation_detail.BuildConfig
import com.serj113.model.Cast
import com.serj113.model.Movie
import com.serj113.model.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchMovieReviewUseCase: FetchMovieReviewUseCase,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchMovieRecommendationsUseCase: FetchMovieRecommendationsUseCase,
    private val fetchMovieSimilarUseCase: FetchMovieSimilarUseCase
) : ViewModel() {

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
    private var listReview = MediatorLiveData<List<Review>>()

    private var movieRecommendations = MutableLiveData<List<Movie>>(listOf())
    private var movieSimilar = MutableLiveData<List<Movie>>(listOf())

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

                        else -> { }
                    }
                }
                .collect()
        }
    }

    private fun fetchMovieRecommendations(movieId: Long) {
        viewModelScope.launch(Dispatchers.Default) {
            fetchMovieRecommendationsUseCase
                .invoke(FetchMovieRecommendationsUseCase.Args(movieId, 1))
                .onEach {
                    when (it) {
                        is Entity.Success -> {
                            movieRecommendations.postValue(it.data.results)
                        }

                        else -> { }
                    }
                }
                .collect()
        }
    }

    private fun fetchMovieSimilar(movieId: Long) {
        viewModelScope.launch(Dispatchers.Default) {
            fetchMovieSimilarUseCase
                .invoke(FetchMovieSimilarUseCase.Args(movieId, 1))
                .onEach {
                    when (it) {
                        is Entity.Success -> {
                            movieSimilar.postValue(it.data.results)
                        }

                        else -> { }
                    }
                }
                .collect()
        }
    }

    private fun fetchReview(movieId: Long) {
        viewModelScope.launch(Dispatchers.Default) {
            fetchMovieReviewUseCase
                .invoke(FetchMovieReviewUseCase.Args(movieId, 1))
                .onEach {
                    when (it) {
                        is Entity.Success -> {
                            listReview.postValue(it.data.results)
                        }

                        else -> { }
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
        fetchReview(movieId)
        fetchMovieRecommendations(movieId)
        fetchMovieSimilar(movieId)
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

    fun getListCast(): LiveData<List<Cast>> = listCast

    fun getListReview(): LiveData<List<Review>> {
        return listReview
    }

    fun getMovieRecommendations(): LiveData<List<Movie>> = movieRecommendations

    fun getMovieSimilar(): LiveData<List<Movie>> = movieSimilar
}
