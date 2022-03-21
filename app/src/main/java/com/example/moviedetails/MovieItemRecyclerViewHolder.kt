package com.example.moviedetails

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

class MovieItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val movieName: TextView = itemView.findViewById(R.id.movieName)
     val releaseYear: TextView = itemView.findViewById(R.id.releaseYear)
     val moviePoster: AppCompatImageView = itemView.findViewById(R.id.moviePoster)
}