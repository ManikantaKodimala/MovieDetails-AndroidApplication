package com.example.moviedetails.data.api

import com.example.moviedetails.data.model.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?")
    suspend fun getMovies(): APIResponse

    @GET("discover/movie?")
    suspend fun getMoviesByReleaseYear(@Query("primary_release_year") primary_release_year:Int): APIResponse

    @GET("search/movie?")
    suspend fun getMoviesByTitle(@Query("query") query: String): APIResponse

}