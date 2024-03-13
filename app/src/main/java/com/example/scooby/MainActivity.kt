package com.example.scooby

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.scooby.databinding.ActivityMainBinding
import com.example.scooby.utils.Constant

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager
    private lateinit var navController: NavController
    private lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Scooby)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = "Test"


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController


        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolBar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationView.setupWithNavController(navController)

        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.toHomeFragment -> navController.navigate(R.id.homeFragment)
                    R.id.toPawsFragment -> navController.navigate(R.id.pawsFragment)
                    R.id.toCommunityFragment -> navController.navigate(R.id.communityFragment)
                    R.id.toBookingFragment -> navController.navigate(R.id.bookingFragment)
                }
                true
            }
        }
        fragmentManager = supportFragmentManager
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> navController.navigate(R.id.homeFragment)
                R.id.nav_search -> navController.navigate(R.id.profileFragment)
                R.id.nav_notifications -> navController.navigate(R.id.profileFragment)
                R.id.nav_favorite -> navController.navigate(R.id.profileFragment)
                R.id.nav_reminders -> navController.navigate(R.id.profileFragment)
                R.id.nav_settings -> navController.navigate(R.id.profileFragment)
                R.id.nav_help -> navController.navigate(R.id.profileFragment)
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}