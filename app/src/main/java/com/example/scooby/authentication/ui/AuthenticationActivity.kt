package com.example.scooby.authentication.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.scooby.R

class AuthenticationActivity : AppCompatActivity() {
       @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           Thread.sleep(3000)
           installSplashScreen()
        setContentView(R.layout.activity_authentication)
    }
}