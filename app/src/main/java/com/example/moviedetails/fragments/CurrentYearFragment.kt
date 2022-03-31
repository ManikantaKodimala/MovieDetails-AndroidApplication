package com.example.moviedetails.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetails.*
import com.example.moviedetails.databinding.FragmentCurrentYearBinding
import com.example.moviedetails.data.api.MovieApi
import com.example.moviedetails.data.repository.MovieRepository
import com.example.moviedetails.data.api.RetrofitClient
import com.example.moviedetails.ui.main.adapters.MovieAdapter
import com.example.moviedetails.ui.main.viewmodels.MovieViewModel
import com.example.moviedetails.ui.main.viewmodels.ViewModelFactory
import com.example.moviedetails.ui.main.views.MovieDescriptionActivity
import com.example.moviedetails.utils.MOVIE

class CurrentYearFragment : Fragment(R.layout.fragment_current_year) {
    private lateinit var binding: FragmentCurrentYearBinding
    private lateinit var movieRepository: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentYearBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isNetworkAvailable = requireContext().isNetworkAvailable()
        movieRepository = MovieRepository(
            RetrofitClient.getClient().create(MovieApi::class.java),
            MovieRoomDataBase.getDatabase(requireContext()).movieDao(),
            isNetworkAvailable
        )
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(movieRepository)
        )[MovieViewModel::class.java]

        val currentYearMoviesListRV = binding.currentYearMoviesList
        currentYearMoviesListRV.layoutManager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }
        val movieAdapter = MovieAdapter()
        currentYearMoviesListRV.adapter = movieAdapter
        movieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(context, MovieDescriptionActivity::class.java)
                intent.putExtra(MOVIE, viewModel.listOfCurrentYearMovies.value?.get(position))
                startActivity(intent)
            }
        })
        viewModel.getCurrentYearMovies()
        viewModel.listOfCurrentYearMovies.observe(viewLifecycleOwner) {
            movieAdapter.upDateMovieListItems(it)
        }
    }
}