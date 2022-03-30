package com.example.moviedetails.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedetails.Movie
import com.example.moviedetails.MovieDiffCallback
import com.example.moviedetails.MovieItemRecyclerViewHolder
import com.example.moviedetails.R

class MovieAdapter :
    RecyclerView.Adapter<MovieItemRecyclerViewHolder>() {
    private var movies = ArrayList<Movie>()
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
    fun upDateMovieListItems(newListOfMovies: List<Movie>){
        val diffCallback = MovieDiffCallback(this.movies,newListOfMovies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        movies.clear()
        movies.addAll(newListOfMovies)
    }
}


