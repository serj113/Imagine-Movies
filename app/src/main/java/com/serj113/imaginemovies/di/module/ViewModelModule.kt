package com.serj113.imaginemovies.di.module

import androidx.lifecycle.ViewModel
import com.serj113.imaginemovies.ui.detail.DetailViewModel
import com.serj113.imaginemovies.ui.list.MovieListViewModel
import com.serj113.imaginemovies.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}