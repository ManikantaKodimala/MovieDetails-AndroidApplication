package com.example.moviedetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetails.Movie
import com.example.moviedetails.MovieAdapter
import com.example.moviedetails.MovieViewModel
import com.example.moviedetails.R
import com.example.moviedetails.databinding.FragmentPoppularBinding
import com.example.moviedetails.network.MovieApi
import com.example.moviedetails.network.MovieRepository
import com.example.moviedetails.network.RetrofitClient
import java.util.ArrayList


class PopularFragment : Fragment(R.layout.fragment_poppular) {

    private lateinit var binding: FragmentPoppularBinding
    private lateinit var movieRepository: MovieRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPoppularBinding.inflate(inflater, container, false)
        movieRepository = MovieRepository(RetrofitClient.getClient().create(MovieApi::class.java))
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.let { ViewModelProvider(it)[MovieViewModel::class.java] }
            ?: throw RuntimeException("Not a Activity")

        val popularMovieListRV = binding.popularMovieList
        popularMovieListRV.layoutManager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }

        val movies = ArrayList<Movie>()
        popularMovieListRV.adapter = MovieAdapter(movies)
        viewModel.getMovies(movieRepository)
        viewModel.listOfMovies.observe(viewLifecycleOwner) {
            popularMovieListRV.adapter = MovieAdapter(it)
        }
    }

}