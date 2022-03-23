package com.example.moviedetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetails.*
import com.example.moviedetails.databinding.FragmentCurrentYearBinding
import com.example.moviedetails.network.MovieApi
import com.example.moviedetails.network.MovieRepository
import com.example.moviedetails.network.RetrofitClient
import java.util.ArrayList

class CurrentYearFragment : Fragment(R.layout.fragment_current_year) {
    lateinit var binding: FragmentCurrentYearBinding
    lateinit var movieRepository: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentYearBinding.inflate(inflater, container, false)
        movieRepository = MovieRepository(RetrofitClient.getClient().create(MovieApi::class.java))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel=ViewModelProvider(this, ViewModelFactory(null,movieRepository)).get(MovieViewModel::class.java)

        val currentYearMoviesListRV = binding.currentYearMoviesList
        currentYearMoviesListRV.layoutManager= LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }
        val movies = ArrayList<Movie>()
        currentYearMoviesListRV.adapter = MovieAdapter(movies)
        viewModel.getCurrentYearMovies()
        viewModel.listOfCurrentYearMovies.observe(viewLifecycleOwner){
            currentYearMoviesListRV.adapter = MovieAdapter(it)
        }

    }

}