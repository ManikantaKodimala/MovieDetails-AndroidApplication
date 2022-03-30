package com.example.moviedetails.data.model

import com.google.gson.annotations.SerializedName

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
){
    fun checkAnyNullValuePresent():Boolean{
        return (this.imageUrl.isNullOrEmpty() or this.releaseDate.isNullOrEmpty() or this.title.isNullOrEmpty() or this.releaseDate.isNullOrEmpty())
    }
}
