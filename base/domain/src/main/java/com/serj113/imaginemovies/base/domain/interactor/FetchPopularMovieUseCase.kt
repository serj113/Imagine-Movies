package com.serj113.imaginemovies.base.domain.interactor

import com.serj113.imaginemovies.base.domain.Entity
import com.serj113.imaginemovies.base.domain.FlowUseCase
import com.serj113.imaginemovies.base.model.MovieList

abstract class FetchPopularMovieUseCase : FlowUseCase<Entity<MovieList>>()