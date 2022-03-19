package com.example.moviedetails

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

class MovieItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val movieName: TextView = itemView.findViewById<TextView>(R.id.movieName)
     val releaseYear: TextView = itemView.findViewById<TextView>(R.id.releaseYear)
     val moviePoster: ImageView = itemView.findViewById<AppCompatImageView>(R.id.moviePoster)

}