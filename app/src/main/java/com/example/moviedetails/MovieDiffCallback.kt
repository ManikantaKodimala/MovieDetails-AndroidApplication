package com.example.moviedetails

import androidx.recyclerview.widget.DiffUtil

class MovieDiffCallback (private val oldListOfMovies:List<Movie> , private val newListOfMovies:List<Movie>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return this.oldListOfMovies.size
    }

    override fun getNewListSize(): Int {
    return this.newListOfMovies.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListOfMovies[oldItemPosition].id == this.newListOfMovies[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie= this.oldListOfMovies[oldItemPosition]
        val newMovie= this.newListOfMovies[newItemPosition]

        return oldMovie.title == newMovie.title
    }

}