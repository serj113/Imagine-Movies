package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCase
import com.serj113.model.MovieList

abstract class FetchPopularMovieUseCase : FlowUseCase<Entity<MovieList>>()