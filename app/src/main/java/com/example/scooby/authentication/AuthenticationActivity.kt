package com.example.scooby.authentication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.example.scooby.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.databinding.ActivityAuthenticationBinding
import com.example.data.Constant
import com.example.scooby.TokenManager

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val token = TokenManager.getAuth(this, Constant.USER_TOKEN)
        if (!token.isNullOrBlank()) {
            goToHome()
        }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
}