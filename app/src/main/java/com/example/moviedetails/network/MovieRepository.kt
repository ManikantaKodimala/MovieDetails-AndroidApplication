package com.example.moviedetails.network

import com.example.moviedetails.Movie

class MovieRepository(private val usersAPI: MovieApi) {
    suspend fun getMovies(): List<Movie> {
        val userResponse = usersAPI.getMovies()
        return convertDTOIntoUIModel(userResponse.movies)
    }

    suspend fun getMoviesByYear(year: Int): List<Movie> {
        val movieResponses = usersAPI.getMoviesByReleaseYear(year)
        return convertDTOIntoUIModel(movieResponses.movies)
    }

    suspend fun getMoviesByTitle(query: String): List<Movie> {
        val movieResponse = usersAPI.getMoviesByTitle(query)
        return convertDTOIntoUIModel(movieResponse.movies)
    }

    private fun convertDTOIntoUIModel(movieResponses: List<MovieResponse>): List<Movie> {
        return movieResponses.filter {
            !it.imageUrl.isNullOrEmpty()
        }.map {
            Movie(
                it.id, it.title, it.language, it.releaseDate, it.overView, it.imageUrl
            )
        }
    }
}



