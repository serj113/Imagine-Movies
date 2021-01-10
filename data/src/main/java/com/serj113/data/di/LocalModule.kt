package com.serj113.data.di

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.serj113.data.local.database.MovieDao
import com.serj113.data.local.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class LocalModule {

    private lateinit var movieDatabase: MovieDatabase

    @Provides
    @Singleton
    internal fun provideMovieDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        movieDatabase = Room.databaseBuilder(
            appContext,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
        return movieDatabase
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(db: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }
}