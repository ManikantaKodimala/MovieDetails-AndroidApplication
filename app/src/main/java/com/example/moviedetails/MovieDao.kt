package com.example.moviedetails


import androidx.room.Insert
import androidx.room.Query
import androidx.room.Dao
import androidx.room.OnConflictStrategy

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity:List<MovieEntity>)

    @Query("select * from movieDetails")
    suspend fun getAllPopularMovies():List<Movie>

    @Query("select * from movieDetails where releaseDate like :releaseDate||'%' ")
    suspend fun getMoviesByReleaseDate(releaseDate: String):List<Movie>

}