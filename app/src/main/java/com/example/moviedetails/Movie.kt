package com.example.moviedetails

import java.util.Date

data class Movie (
        val id: Int,
        val title: String,
        val language: String,
        val releaseDate: Date,
        val overView: String,
        val imageUrl:String
)