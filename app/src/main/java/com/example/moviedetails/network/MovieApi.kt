package com.example.moviedetails.network

import com.example.moviedetails.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?")
    suspend fun getMovies(@Query("api_key") api_key:String=API_KEY): APIResponse

    @GET("discover/movie?")
    suspend fun getMoviesByReleaseYear(@Query("primary_release_year") primary_release_year:Int, @Query("api_key") api_key:String=API_KEY):APIResponse

    @GET("search/movie?")
    suspend fun getMoviesByTitle(@Query("query") query: String,@Query("api_key") api_key:String=API_KEY):APIResponse

}