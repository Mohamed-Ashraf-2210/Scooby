package com.example.scooby

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.welcome_id)
        val spanString = SpannableString("Welcome to Scooby \nyour pet home!")
        spanString.setSpan(ForegroundColorSpan(Color.rgb(81,57,115)),11,17,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spanString
    }
}