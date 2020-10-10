package com.serj113.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serj113.data.local.entity.MovieLocal

@Database(entities = [MovieLocal::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        internal const val DATABASE_NAME = "movie_database"
    }
}