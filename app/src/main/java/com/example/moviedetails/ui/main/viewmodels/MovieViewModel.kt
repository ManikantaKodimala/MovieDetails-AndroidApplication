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
import java.util.*

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _listOfMovies = MutableLiveData<List<Movie>>()
    private val _listOfCurrentYearMovies=MutableLiveData<List<Movie>>()
    private val _error = MutableLiveData<String>()

    val listOfMovies: MutableLiveData<List<Movie>> = _listOfMovies
    val listOfCurrentYearMovies:LiveData<List<Movie>> = _listOfCurrentYearMovies
    val error: LiveData<String> = _error

    init {
        getPopularMovies()
        getCurrentYearMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO){
            when(val moviesResponse = movieRepository.getMovies()){
                is ResponseData.Movies ->
                    _listOfMovies.postValue(moviesResponse.listOfMovies)
                is ResponseData.Error ->
                    _error.value=moviesResponse.customException.message
            }
        }
    }

    fun getCurrentYearMovies() {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        viewModelScope.launch(Dispatchers.IO){
            when(val moviesResponse = movieRepository.getMoviesByYear(year)){
                is ResponseData.Movies ->
                    _listOfCurrentYearMovies.postValue(moviesResponse.listOfMovies)
                is ResponseData.Error ->
                    _error.value=moviesResponse.customException.message
            }
        }
    }
}
