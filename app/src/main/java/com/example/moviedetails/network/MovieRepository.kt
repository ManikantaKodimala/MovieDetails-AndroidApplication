package com.example.moviedetails.network

import com.example.moviedetails.*

class MovieRepository(private val usersAPI: MovieApi, private val movieDao: MovieDao,private val isNetWorkAvailable :Boolean) {
    suspend fun getMovies(): ResponseData {
        if (isNetWorkAvailable) {
            return try {
                val movieResponse = usersAPI.getMovies()
                movieDao.insert(convertDTOIntoDataModel(movieResponse.movies))
                ResponseData.Movies(convertDTOIntoUIModel(movieResponse.movies))
            } catch (exception: Exception) {
                ResponseData.Error(exception)
            }
        }
        return ResponseData.Movies(movieDao.getAllPopularMovies())
    }


    suspend fun getMoviesByYear(year: Int): ResponseData {
        if (isNetWorkAvailable) {
            return try {
                val movieResponses = usersAPI.getMoviesByReleaseYear(year)
                movieDao.insert(convertDTOIntoDataModel(movieResponses.movies))
                ResponseData.Movies(convertDTOIntoUIModel(movieResponses.movies))
            } catch (exception: Exception) {
                ResponseData.Error(exception)
            }
        }
        return ResponseData.Movies(movieDao.getMoviesByReleaseDate(year.toString()))
    }

    suspend fun getMoviesByTitle(query: String): ResponseData {
        return try {
            val movieResponse = usersAPI.getMoviesByTitle(query)
            ResponseData.Movies(convertDTOIntoUIModel(movieResponse.movies))
        }catch (exception:Exception){
            ResponseData.Error(exception)
        }
    }

    private fun convertDTOIntoUIModel(movieResponses: List<MovieResponse>): List<Movie> {
        return movieResponses.filter {
            !it.checkAnyNullValuePresent()
        }.map {
            val imageUrl = BASEIMAGEURL + it.imageUrl
            Movie(
                it.id, it.title, it.language, it.releaseDate, it.overView, imageUrl
            )
        }
    }

    private fun convertDTOIntoDataModel(movieResponses: List<MovieResponse>): List<MovieEntity> {
        return movieResponses.filter {
            !it.checkAnyNullValuePresent()
        }.map {
            val imageUrl = BASEIMAGEURL + it.imageUrl
            MovieEntity(
                it.id, it.title, it.language, it.releaseDate, it.overView, imageUrl
            )
        }
    }
}



