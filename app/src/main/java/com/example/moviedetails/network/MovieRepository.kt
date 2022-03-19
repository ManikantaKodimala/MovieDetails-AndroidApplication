package com.example.moviedetails.network

import android.util.Log
import com.example.moviedetails.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Year
import java.util.*

class MovieRepository( val usersAPI: MovieApi) {
    suspend fun getMovies(): List<Movie> {
        val userReponse =  withContext(Dispatchers.IO){usersAPI.getMovies()}
        return convertDTOIntoUIModel(userReponse.movies)}


    private fun convertDTOIntoUIModel(movieResponses: List<MovieResponse>): List<Movie> {
        return movieResponses.map {
            Movie(
                it.id, it.title, it.language, it.releaseDate, it.overView,it.imageUrl
            )
        }
    }

    suspend fun getMoviesByYear(): List<Movie> {
        val year=Calendar.getInstance().get(Calendar.YEAR)
       Log.i("year=",year.toString())
        val movieResponses = withContext(Dispatchers.IO){usersAPI.getCurrentYearMovies()}
        return convertDTOIntoUIModel( movieResponses.movies)
    }
}