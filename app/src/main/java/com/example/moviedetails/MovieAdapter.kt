package com.example.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieItemRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieItemRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieItemRecyclerViewHolder, position: Int) {
        holder.movieName.text = movies[position].title
        holder.releaseYear.text = movies[position].releaseDate
        val imageUrl = movies[position].imageUrl
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.moviePoster)
    }

}


