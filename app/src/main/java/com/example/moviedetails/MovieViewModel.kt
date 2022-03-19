package com.example.moviedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedetails.network.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MovieViewModel : ViewModel() {

    private val _listOfMovies = MutableLiveData(ListOfMovies(listOf(Movie(0, "", "", Date(), "",""))))
    private val _listOfCurrentYearMovies=MutableLiveData(ListOfMovies(listOf(Movie(0, "", "", Date(), "",""))))


    val listOfMovies: MutableLiveData<ListOfMovies> = _listOfMovies
    val listOfCurrentYearMovies:LiveData<ListOfMovies> = _listOfCurrentYearMovies

    fun getMovies(movieRepository: MovieRepository) {
        CoroutineScope(Dispatchers.IO).launch {
            _listOfMovies.postValue(ListOfMovies(movieRepository.getMovies()))
            Log.i("movies=", _listOfMovies.value.toString())
        }
    }

    fun getCurrentYearMovies(movieRepository : MovieRepository) {
        CoroutineScope(Dispatchers.IO).launch {
            _listOfCurrentYearMovies.postValue(ListOfMovies(movieRepository.getMoviesByYear()))
            Log.i("movies=", _listOfMovies.value.toString())
        }
    }


}