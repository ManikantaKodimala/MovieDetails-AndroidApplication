package com.example.moviedetails.network

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("original_language")
    val language: String,

    @SerializedName("release_date",)
    val releaseDate: String,

    @SerializedName("original_title")
    val title: String,

    @SerializedName("overview")
    val overView: String,

    @SerializedName("poster_path")
    val imageUrl:String
)
