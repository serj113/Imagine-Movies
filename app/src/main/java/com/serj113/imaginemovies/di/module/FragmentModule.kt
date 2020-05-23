package com.serj113.imaginemovies.di.module

import com.serj113.imaginemovies.ui.detail.DetailFragment
import com.serj113.imaginemovies.ui.list.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMovieList(): MovieListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetail(): DetailFragment
}