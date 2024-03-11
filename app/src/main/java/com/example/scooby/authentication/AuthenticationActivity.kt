package com.example.scooby.authentication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.utils.Constant
import com.example.scooby.utils.TokenManager

class AuthenticationActivity : AppCompatActivity() {
       @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           Thread.sleep(3000)
           installSplashScreen()
           val token = TokenManager.getAuth(this,Constant.USER_TOKEN)
           if (!token.isNullOrBlank()) {
               goToHome()
           }
        setContentView(R.layout.activity_authentication)
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
}