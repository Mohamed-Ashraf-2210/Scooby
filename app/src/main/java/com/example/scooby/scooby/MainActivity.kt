package com.example.scooby.scooby

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.scooby.R
import com.example.scooby.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Scooby)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.toHomeFragment -> {
                    navController.navigate(R.id.toHomeFragment)
                    true
                }

                R.id.toPawsFragment -> {
                    navController.navigate(R.id.toPawsFragment)
                    true
                }

                R.id.toCommunityFragment -> {
                    navController.navigate(R.id.toCommunityFragment)
                    true
                }

                R.id.toBookingFragment -> {
                    navController.navigate(R.id.toBookingFragment)
                    true
                }

                else -> false
            }
        }

        binding.apply {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                toolBar,
                R.string.nav_open,
                R.string.nav_close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            setupNavigationView()
            navigationView.setupWithNavController(navController)
        }

        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun setupNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    // Navigate to the profile destination
                    navController.navigate(R.id.profileFragment)
                }

                R.id.nav_settings -> {
                    // Navigate to the settings destination
                }
                // Add more cases for other menu items if needed
            }
            // Close the navigation drawer
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true // Return true to indicate that the item click has been handled
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun hideBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}