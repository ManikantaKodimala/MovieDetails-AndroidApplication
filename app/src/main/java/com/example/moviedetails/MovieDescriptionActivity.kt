package com.example.moviedetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieName = intent.extras?.getString(MovieName)
        val overView = intent.extras?.getString(OverView)
        val imageUrl = intent.extras?.getString(ImageUrl)
        val imageView = findViewById<ImageView>(R.id.moviePosterDiscription)
        findViewById<TextView>(R.id.movieOverview).text = overView
        findViewById<TextView>(R.id.title).text = movieName
        val imagePath = "https://image.tmdb.org/t/p/w200$imageUrl"
        Glide.with(this).load(imagePath).into(imageView)
    }
}