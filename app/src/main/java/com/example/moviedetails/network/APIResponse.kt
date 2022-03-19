package com.example.moviedetails.network

import com.google.gson.annotations.SerializedName

data class APIResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val movies: List<MovieResponse>

)
