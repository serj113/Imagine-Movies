package com.serj113.imaginemovies.base.data.di

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