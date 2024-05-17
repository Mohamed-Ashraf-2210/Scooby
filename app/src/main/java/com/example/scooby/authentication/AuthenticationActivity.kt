package com.example.scooby.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.data.Constant
import com.example.scooby.R
import com.example.data.local.TokenManager
import com.example.scooby.scooby.MainActivity

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController

        val token = TokenManager.getAuth(this, Constant.USER_TOKEN)
        if (!token.isNullOrBlank()) {
            goToHome()
        }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        finish()
        startActivity(intent)
    }
}