package com.example.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedetails.network.MovieRepository
import kotlinx.coroutines.launch
import java.util.*

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _listOfMovies = MutableLiveData<List<Movie>>()
    private val _listOfCurrentYearMovies=MutableLiveData<List<Movie>>()
    private val _error = MutableLiveData<String>()

    val listOfMovies: MutableLiveData<List<Movie>> = _listOfMovies
    val listOfCurrentYearMovies:LiveData<List<Movie>> = _listOfCurrentYearMovies
    val error: LiveData<String> = _error

    fun getMovies() {
        viewModelScope.launch {
            when(val moviesResponse = movieRepository.getMovies()){
                is ResponseData.Movies ->
                    _listOfMovies.postValue(moviesResponse.listOfMovies)
                is ResponseData.Error->
                    _error.value=moviesResponse.customException.message
            }
        }
    }

    fun getCurrentYearMovies() {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        viewModelScope.launch{
            when(val moviesResponse = movieRepository.getMoviesByYear(year)){
                is ResponseData.Movies ->
                    _listOfCurrentYearMovies.value=moviesResponse.listOfMovies
                is ResponseData.Error->
                    _error.value=moviesResponse.customException.message
            }
        }
    }
}
