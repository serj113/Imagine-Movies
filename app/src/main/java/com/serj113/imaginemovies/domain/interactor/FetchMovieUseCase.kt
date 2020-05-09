package com.serj113.imaginemovies.domain.interactor

import com.serj113.imaginemovies.domain.base.BaseUseCase
import com.serj113.imaginemovies.domain.entity.Movie
import com.serj113.imaginemovies.domain.base.PagedEntity

interface FetchMovieUseCase : BaseUseCase<PagedEntity<Movie>>