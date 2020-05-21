package com.serj113.imaginemovies.domain.interactor

import com.serj113.imaginemovies.domain.base.MediatorUseCase
import com.serj113.imaginemovies.domain.entity.Movie
import com.serj113.imaginemovies.domain.base.PagedEntity

abstract class FetchMovieUseCase : MediatorUseCase<PagedEntity<Movie>>()