package com.example.moviedetails

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
        val id: Int,
        val title: String,
        val language: String,
        val releaseDate: String,
        val overView: String,
        val imageUrl:String
):Parcelable