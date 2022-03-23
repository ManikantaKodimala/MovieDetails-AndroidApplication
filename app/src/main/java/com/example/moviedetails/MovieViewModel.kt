package com.example.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedetails.network.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MovieViewModel: ViewModel() {

    private val _listOfMovies = MutableLiveData<List<Movie>>()
    private val _listOfCurrentYearMovies=MutableLiveData<List<Movie>>()

    val listOfMovies: MutableLiveData<List<Movie>> = _listOfMovies
    val listOfCurrentYearMovies:LiveData<List<Movie>> = _listOfCurrentYearMovies

    fun getMovies(movieRepository: MovieRepository) {
        CoroutineScope(Dispatchers.IO).launch {
            _listOfMovies.postValue(movieRepository.getMovies())
        }
    }

    fun getCurrentYearMovies(movieRepository : MovieRepository) {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        viewModelScope.launch{
            _listOfCurrentYearMovies.postValue(movieRepository.getMoviesByYear(year))
        }
    }
}
