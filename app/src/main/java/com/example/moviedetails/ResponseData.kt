package com.example.moviedetails

sealed class ResponseData{
    class Movies(val listOfMovies:List<Movie>): ResponseData()
    class Error(val customException: Exception): ResponseData()
}
