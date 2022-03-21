package com.example.moviedetails.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?api_key=293de9a7976623782dc890d49b4a50f4")
    suspend fun getMovies(): APIResponse

    @GET("discover/movie?api_key=293de9a7976623782dc890d49b4a50f4")
    suspend fun getMoviesByReleaseYear(@Query("primary_release_year") primary_release_year:Int):APIResponse

    @GET("search/movie?api_key=293de9a7976623782dc890d49b4a50f4")
    suspend fun getMoviesByTitle(@Query("query") query: String):APIResponse

}