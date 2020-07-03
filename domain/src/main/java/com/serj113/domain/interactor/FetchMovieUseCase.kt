package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCase
import com.serj113.domain.entity.Movie

abstract class FetchMovieUseCase : FlowUseCase<Entity<List<Movie>>>()