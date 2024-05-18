package com.example.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.data.R

@SuppressLint("StaticFieldLeak")
object TokenManager {

    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    private val prefs: SharedPreferences
        get() = context.getSharedPreferences("Scoopy", Context.MODE_PRIVATE)

    fun saveAuth(key: String, value: String) {
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getAuth( key: String): String? {

        return prefs.getString(key, null)
    }

    fun clearToken(context: Context) {
        val editor = context.getSharedPreferences(
            "Scoopy",
            Context.MODE_PRIVATE
        ).edit()
        editor.clear()
        editor.apply()
    }
}