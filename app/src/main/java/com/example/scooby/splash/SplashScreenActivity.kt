package com.example.scooby.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.data.local.TokenManager
import com.example.scooby.R
import com.example.scooby.authentication.AuthenticationActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        TokenManager.initialize(this)
        Log.d("checkApp","initialized Shared")
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}