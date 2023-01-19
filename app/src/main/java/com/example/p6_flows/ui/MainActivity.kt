package com.example.p6_flows.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.p6_flows.R
import com.example.p6_flows.databinding.ActivityMainBinding
import com.example.p6_flows.ui.fragment.MainFragment
import com.example.p6_flows.ui.fragment.MoviesMostSeenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configBottomNavigationView()
        configNavControllerDestinations()



    }

    private fun configBottomNavigationView() {
        with(binding) {
            val navView: BottomNavigationView = binding.navView

            navController = findNavController(navHostFragmentActivityMain.id)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.menu_item_main, R.id.menu_item_most_seen_movies, R.id.menu_item_lupa
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)


            navView.setBackgroundColor(getColor(R.color.teal_200))
            navView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_item_main -> {
                        val badgeDrawable = binding.navView.getOrCreateBadge(it.itemId)
                        badgeDrawable.isVisible = true
                        badgeDrawable.clearNumber()
                        true
                    }
                    R.id.menu_item_most_seen_movies -> {
                        true
                    }
                    R.id.menu_item_lupa -> {
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun configNavControllerDestinations() {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            //topAppBar.isVisible = arguments?.getBoolean("ShowAppBar", false) == true

            when (destination.id) {
                R.id.mainFragment -> {
                    //topAppBar.visibility = View.VISIBLE
                    supportActionBar?.setTitle(R.string.mainFragmentMenuNavigationButton)
                    //topAppBar.menu.findItem(R.id.search).isVisible = true
                }
                R.id.moviesMostSeenFragment -> {
                    //topAppBar.visibility = View.VISIBLE
                    supportActionBar?.setTitle(R.string.moviesMostSeenFragmentMenuNavigationButton)

                }
            }
        }

    }

}