package com.example.moviedetails.ui.main.viewholders

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.R
import com.example.moviedetails.ui.main.adapters.MovieAdapter

class MovieItemRecyclerViewHolder(itemView: View, listener: MovieAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
     val movieName: TextView = itemView.findViewById(R.id.movieName)
     val releaseYear: TextView = itemView.findViewById(R.id.releaseYear)
     val moviePoster: AppCompatImageView = itemView.findViewById(R.id.moviePoster)
     init {
          itemView.setOnClickListener {
               listener.onItemClick(adapterPosition)
          }
     }
}