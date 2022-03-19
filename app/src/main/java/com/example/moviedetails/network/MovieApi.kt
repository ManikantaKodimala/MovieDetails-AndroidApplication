package com.example.moviedetails.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie?sort_by=popularity.desc&api_key=293de9a7976623782dc890d49b4a50f4")
    suspend fun getMovies(): APIResponse

    @GET("movie?primary_release_year=2022&api_key=293de9a7976623782dc890d49b4a50f4")
    suspend fun getCurrentYearMovies():APIResponse

}