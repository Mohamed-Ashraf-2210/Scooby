package com.example.data.local

import android.content.Context
import com.example.data.R

object TokenManager {

    val prefs = context.getSharedPreferences(
        "Scoopy",
        Context.MODE_PRIVATE
    )
    fun saveAuth(context: Context, key: String, value: String) {
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getAuth(context: Context, key: String): String? {

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