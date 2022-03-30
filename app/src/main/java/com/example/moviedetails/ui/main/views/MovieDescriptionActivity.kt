package com.example.moviedetails.ui.main.views

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviedetails.utils.MOVIE
import com.example.moviedetails.Movie
import com.example.moviedetails.R

class MovieDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieDetails = intent.extras?.getParcelable<Movie>(MOVIE)
        val imageView = findViewById<ImageView>(R.id.moviePosterDiscription)
        findViewById<TextView>(R.id.movieOverview).text =movieDetails?.overView
        findViewById<TextView>(R.id.title).text = movieDetails?.title
        val imagePath = movieDetails?.imageUrl
        Glide.with(this).load(imagePath).into(imageView)
    }
}