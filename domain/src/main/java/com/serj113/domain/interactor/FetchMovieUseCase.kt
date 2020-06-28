package com.serj113.domain.interactor

import com.serj113.domain.base.FlowUseCase
import com.serj113.domain.base.PagedEntity
import com.serj113.domain.entity.Movie

abstract class FetchMovieUseCase : FlowUseCase<PagedEntity<Movie>>()