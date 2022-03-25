package com.example.moviedetails.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetails.*
import com.example.moviedetails.databinding.FragmentPoppularBinding
import com.example.moviedetails.network.MovieApi
import com.example.moviedetails.network.MovieRepository
import com.example.moviedetails.network.RetrofitClient


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

        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(null, movieRepository)
        )[MovieViewModel::class.java]
        val popularMovieListRV = binding.popularMovieList
        popularMovieListRV.layoutManager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }

        val movies = ArrayList<Movie>()
        val movieAdapter = MovieAdapter(movies)
        popularMovieListRV.adapter = movieAdapter
        viewModel.getMovies()
        viewModel.listOfMovies.observe(viewLifecycleOwner) {
            popularMovieListRV.adapter = MovieAdapter(it)
        }
        popularMovieListRV.addOnItemTouchListener(
            CustomRecyclerItemClickListener(
                context,
                popularMovieListRV,
                object : CustomRecyclerItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View?, position: Int) {

                        val intent = Intent(context, MovieDescriptionActivity::class.java)
                        intent.putExtra(MOVIE, viewModel.listOfMovies.value?.get(position))
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                    }
                })
        )
    }
}