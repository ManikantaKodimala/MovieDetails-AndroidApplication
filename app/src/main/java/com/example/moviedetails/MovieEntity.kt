package com.example.moviedetails

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieDetails")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String ,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,
    @ColumnInfo(name = "overView")
    val overView: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?
)
