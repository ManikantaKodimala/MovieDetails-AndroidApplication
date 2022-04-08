package com.example.moviedetails.ui.main.views

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetails.*
import com.example.moviedetails.data.api.MovieApi
import com.example.moviedetails.data.repository.MovieRepository
import com.example.moviedetails.data.api.RetrofitClient
import com.example.moviedetails.databinding.ActivitySearchableBinding
import com.example.moviedetails.ui.main.adapters.MovieAdapter
import com.example.moviedetails.ui.main.viewmodels.SearchViewModel
import com.example.moviedetails.ui.main.viewmodels.ViewModelFactory
import com.example.moviedetails.utils.MOVIE

class SearchableActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var  viewModel:SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val searchRV = binding.searchResults
        val movieRepository =
            MovieRepository(
                RetrofitClient.getClient().create(MovieApi::class.java),
                MovieRoomDataBase.getDatabase(this).movieDao(),
                isNetworkAvailable()
            )
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(movieRepository)
        )[SearchViewModel::class.java]

        searchRV.layoutManager =
            LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        val movieAdapter = MovieAdapter()
        searchRV.adapter = movieAdapter
        movieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(baseContext, MovieDescriptionActivity::class.java)
                intent.putExtra(MOVIE, viewModel.listOfSearchedMoviesByTitle.value?.get(position))
                startActivity(intent)
            }
        })


        viewModel.listOfSearchedMoviesByTitle.observe(this) {
            movieAdapter.upDateMovieListItems(it)
        }
        viewModel.error.observe(this) {
            makeToast(it)
        }
    }

    private fun makeToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_mennu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView =
            MenuItemCompat.getActionView(menu.findItem(R.id.search)) as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.search){
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (!query.isNullOrEmpty()){viewModel.searchMovie(query)}
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if(!newText.isNullOrEmpty()){
                        viewModel.searchMovie(newText)
                    }
                    return false
                }
            })
        }
        return super.onOptionsItemSelected(item)
    }
}

