package com.example.moviedetails.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedetails.Movie
import com.example.moviedetails.ResponseData
import com.example.moviedetails.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _listOfSearchedMoviesByTitle= MutableLiveData(listOf(Movie(0, "", "", "", "","")))
    val listOfSearchedMoviesByTitle: LiveData<List<Movie>> = _listOfSearchedMoviesByTitle
    private val _error = MutableLiveData<String>()
    val error:LiveData<String> = _error
    fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val movieResponseData=movieRepository.getMoviesByTitle(query)){
                is ResponseData.Movies ->
                    _listOfSearchedMoviesByTitle.postValue(movieResponseData.listOfMovies)
                is ResponseData.Error ->
                    _error.postValue(movieResponseData.customException.message)
            }

        }
    }
}