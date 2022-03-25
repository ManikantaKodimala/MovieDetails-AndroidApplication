package com.example.moviedetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.databinding.ActivitySearchableBinding
import com.example.moviedetails.network.MovieApi
import com.example.moviedetails.network.MovieRepository
import com.example.moviedetails.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_searchable.*

class SearchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)
        val binding = ActivitySearchableBinding.inflate(layoutInflater)

        val searchRV = findViewById<RecyclerView>(R.id.searchResults)
        val movieRepository =
            MovieRepository(RetrofitClient.getClient().create(MovieApi::class.java))
        val viewModel=ViewModelProvider(this,ViewModelFactory(null,movieRepository)).get(SearchViewModel::class.java)

        searchRV.layoutManager =
            LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }

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
            searchRV.adapter = MovieAdapter(it)
        }

        searchRV.addOnItemTouchListener(
            CustomRecyclerItemClickListener(
                this,
                searchRV,
                object : CustomRecyclerItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View?, position: Int) {

                        val intent = Intent(this@SearchableActivity,MovieDescriptionActivity::class.java)
                        intent.putExtra(MOVIE, viewModel.listOfSearchedMoviesByTitle.value?.get(position))
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                    }
                })
        )
    }
    
}

