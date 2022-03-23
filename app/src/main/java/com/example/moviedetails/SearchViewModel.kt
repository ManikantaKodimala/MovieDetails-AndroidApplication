package com.example.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedetails.network.MovieRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _listOfSearchedMoviesByTitle= MutableLiveData(listOf(Movie(0, "", "", "", "","")))
    val listOfSearchedMoviesByTitle: LiveData<List<Movie>> = _listOfSearchedMoviesByTitle

    fun searchMovie(query: String) {
        viewModelScope.launch {
            _listOfSearchedMoviesByTitle.postValue(movieRepository.getMoviesByTitle(query))
        }
    }
}