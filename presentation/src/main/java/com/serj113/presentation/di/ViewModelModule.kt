package com.serj113.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.serj113.presentation.ui.detail.MovieDetailViewModel
import com.serj113.presentation.ui.list.MovieListViewModel
import com.serj113.presentation.util.ViewModelFactory
import com.serj113.presentation.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@InstallIn(ApplicationComponent::class)
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindDetailViewModel(detailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}