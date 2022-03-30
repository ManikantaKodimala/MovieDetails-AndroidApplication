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
import com.example.moviedetails.databinding.FragmentPoppularBinding
import com.example.moviedetails.data.api.MovieApi
import com.example.moviedetails.data.repository.MovieRepository
import com.example.moviedetails.data.api.RetrofitClient
import com.example.moviedetails.ui.main.adapters.MovieAdapter
import com.example.moviedetails.ui.main.viewmodels.MovieViewModel
import com.example.moviedetails.ui.main.viewmodels.ViewModelFactory
import com.example.moviedetails.ui.main.views.MovieDescriptionActivity
import com.example.moviedetails.utils.MOVIE


class PopularFragment : Fragment(R.layout.fragment_poppular) {

    private lateinit var binding: FragmentPoppularBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPoppularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isNetworkAvailable = requireContext().isNetworkAvailable()
        val movieRepository = MovieRepository(
            RetrofitClient.getClient().create(MovieApi::class.java),
            MovieRoomDataBase.getDatabase(requireContext()).movieDao(), isNetworkAvailable
        )
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(movieRepository)
        )[MovieViewModel::class.java]
        val popularMovieListRV = binding.popularMovieList
        popularMovieListRV.layoutManager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }
        val movieAdapter = MovieAdapter()
        popularMovieListRV.adapter = movieAdapter
        viewModel.getMovies()
        viewModel.listOfMovies.observe(viewLifecycleOwner) {
            movieAdapter.upDateMovieListItems(it)

        }
        popularMovieListRV.addOnItemTouchListener(
            CustomRecyclerItemClickListener(
                context,
                object : CustomRecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(position: Int) {

                        val intent = Intent(context, MovieDescriptionActivity::class.java)
                        intent.putExtra(MOVIE, viewModel.listOfMovies.value?.get(position))
                        startActivity(intent)
                    }
                }
            )
        )
    }

//     fun isNetworkAvailable(): Boolean {
//        val connectivityManager =
//            requireActivity().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        val activeNetworkInfo = connectivityManager.activeNetworkInfo
//        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
//    }
}