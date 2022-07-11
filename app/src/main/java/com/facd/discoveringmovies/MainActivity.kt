package com.facd.discoveringmovies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.facd.discoveringmovies.databinding.ActivityMainBinding
import com.facd.discoveringmovies.listmovies.ListMovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var host: NavHostFragment
    private lateinit var binding: ActivityMainBinding

    val viewModel: ListMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        host = supportFragmentManager
            .findFragmentById(binding.navHostFragment.id) as NavHostFragment? ?: return
        navController = host.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = resources.getString(R.string.hint_field_search_movie)
        searchView.setBackgroundColor(getColor(R.color.white))

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.list_movies_fragment -> {
                    searchItem.expandActionView()
                    searchItem.isVisible = false
                }

                R.id.movie_details_fragment -> {
                    searchItem.isVisible = false
                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(movie: String?): Boolean {

                if (movie != null) {
                    viewModel.searchMovieByTitle(movie)

                    searchItem.expandActionView()
                    searchView.setQuery(movie, false)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                navController.navigate(R.id.list_movies_fragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}