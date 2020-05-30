package com.serj113.presentation.di

import com.serj113.presentation.ui.detail.MovieDetailFragment
import com.serj113.presentation.ui.list.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMovieList(): MovieListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetail(): MovieDetailFragment
}