package com.serj113.imaginemovies.base.data.di

import android.content.Context
import androidx.room.Room
import com.serj113.imaginemovies.base.data.local.database.MovieDao
import com.serj113.imaginemovies.base.data.local.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {

    @Provides
    @Singleton
    internal fun provideMovieDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(
            appContext,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(db: MovieDatabase): MovieDao {
        return db.movieDao()
    }
}
