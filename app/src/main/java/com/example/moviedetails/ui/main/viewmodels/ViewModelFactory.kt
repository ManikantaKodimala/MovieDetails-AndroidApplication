package com.example.moviedetails.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedetails.data.repository.MovieRepository

class ViewModelFactory(private val movieRepository: MovieRepository?):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(movieRepository!!) as T
        }
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(movieRepository!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}