package com.example.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.moviedetails.databinding.ActivityMovieDescriptionBinding

class MovieDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        val binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        val extras = intent.extras?.getString("movieName")
        Log.i("intents values",extras.toString())
        findViewById<TextView>(R.id.movieOverview).setText(extras)

    }
}