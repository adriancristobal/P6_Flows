package com.example.p6_flows.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.p6_flows.R
import com.example.p6_flows.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configBottomNavigationView()
        configNavControllerDestinations()



    }

    private fun configBottomNavigationView() {
        with(binding) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
            navController = navHostFragment.navController
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.menu_item_main,
                    R.id.menu_item_most_seen_movies,
                    R.id.menu_item_lupa
                )
            )
           // setupActionBarWithNavController(navController, appBarConfiguration)
           // navView.setupWithNavController(navController)

            navView.setBackgroundColor(getColor(R.color.teal_200))
            navView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_item_main -> {
                        navController.navigate(R.id.mainFragment)
                        true
                    }
                    R.id.menu_item_most_seen_movies -> {
                        navController.navigate(R.id.moviesMostSeenFragment)
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