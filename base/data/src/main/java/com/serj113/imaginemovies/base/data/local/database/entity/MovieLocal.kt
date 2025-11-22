package com.serj113.imaginemovies.base.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serj113.imaginemovies.base.model.Movie
import com.serj113.imaginemovies.base.model.MovieList

@Entity(tableName = "movie")
data class MovieLocal(
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)

fun MovieLocal.toEntity(): Movie {
    return Movie(
        adult,
        backdropPath,
        listOf(),
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun List<MovieLocal>.toEntity(): MovieList {
    val movieList = map { it.toEntity() }
    return MovieList(0,  movieList, 0, movieList.size)
}
