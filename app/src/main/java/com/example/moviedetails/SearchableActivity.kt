package com.example.moviedetails

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.network.MovieApi
import com.example.moviedetails.network.MovieRepository
import com.example.moviedetails.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_searchable.*

class SearchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

        val searchRV = findViewById<RecyclerView>(R.id.searchResults)
        val movieRepository =
            MovieRepository(
                RetrofitClient.getClient().create(MovieApi::class.java),
                MovieRoomDataBase.getDatabase(this).movieDao(),
                isNetworkAvailable()
            )
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(movieRepository)
        ).get(SearchViewModel::class.java)

        searchRV.layoutManager =
            LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        val movieAdapter = MovieAdapter()
        searchRV.adapter = movieAdapter
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchMovie(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        viewModel.listOfSearchedMoviesByTitle.observe(this) {
            movieAdapter.upDateMovieListItems(it)
        }
        viewModel.error.observe(this){
            makeToast(it)
        }
        searchRV.addOnItemTouchListener(
            CustomRecyclerItemClickListener(
                this,
                searchRV,
                object : CustomRecyclerItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View?, position: Int) {

                        val intent =
                            Intent(this@SearchableActivity, MovieDescriptionActivity::class.java)
                        intent.putExtra(
                            MOVIE,
                            viewModel.listOfSearchedMoviesByTitle.value?.get(position)
                        )
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                    }
                })
        )
    }

    private fun makeToast(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

}

