package com.serj113.data.di

import android.content.Context
import androidx.room.Room
import com.serj113.data.local.MovieDao
import com.serj113.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//@InstallIn(ApplicationComponent::class)
//@Module
//class FirebaseModule {
//    @Provides
//    @Singleton
//    internal fun provideMovieDatabase(@ApplicationContext appContext: Context): MovieDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            MovieDatabase::class.java,
//            MovieDatabase.DATABASE_NAME
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
//        return movieDatabase.movieDao()
//    }
//}