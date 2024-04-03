package com.example.scooby.scooby

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

                R.id.toProfileFragment -> {
                    navController.navigate(R.id.toProfileFragment)
                    true
                }

                else -> false
            }
        }

    }

    fun hideBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}