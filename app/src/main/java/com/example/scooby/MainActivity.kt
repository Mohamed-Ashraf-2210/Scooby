package com.example.scooby

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.scooby.databinding.ActivityMainBinding
import com.example.scooby.scooby.ui.BookingFragment
import com.example.scooby.scooby.ui.CommunityFragment
import com.example.scooby.scooby.ui.FavoriteFragment
import com.example.scooby.scooby.ui.HelpFragment
import com.example.scooby.scooby.ui.HomeFragment
import com.example.scooby.scooby.ui.NotificationsFragment
import com.example.scooby.scooby.ui.PawsFragment
import com.example.scooby.scooby.ui.ProfileFragment
import com.example.scooby.scooby.ui.RemindersFragment
import com.example.scooby.scooby.ui.SettingsFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Scooby)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolBar,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.toHomeFragment -> openFragment(HomeFragment())
                R.id.toPawsFragment -> openFragment(PawsFragment())
                R.id.toCommunityFragment -> openFragment(CommunityFragment())
                R.id.toBookingFragment -> openFragment(BookingFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())
        supportActionBar?.title = "Test"

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> openFragment(ProfileFragment())
            R.id.nav_search -> openFragment(SettingsFragment())
            R.id.nav_notifications -> openFragment(NotificationsFragment())
            R.id.nav_favorite -> openFragment(FavoriteFragment())
            R.id.nav_reminders -> openFragment(RemindersFragment())
            R.id.nav_settings -> openFragment(SettingsFragment())
            R.id.nav_help -> openFragment(HelpFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
        super.onBackPressed()
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}