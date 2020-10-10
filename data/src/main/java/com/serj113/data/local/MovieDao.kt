package com.serj113.data.local

import androidx.room.Dao
import androidx.room.Query
import com.serj113.data.local.entity.MovieLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("select * from movie")
    fun getAllMovie(): Flow<List<MovieLocal>>
}