package com.serj113.base.domain.interactor

import com.serj113.base.domain.Entity
import com.serj113.base.domain.FlowUseCase
import com.serj113.model.MovieList

abstract class FetchPopularMovieUseCase : FlowUseCase<Entity<MovieList>>()