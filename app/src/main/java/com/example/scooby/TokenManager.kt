package com.example.scooby

import android.content.Context

object TokenManager {

    fun saveAuth(context: Context, key: String, value: String) {
        val prefs = context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getAuth(context: Context, key: String): String? {
        val prefs = context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        return prefs.getString(key, null)
    }

    fun clearToken(context: Context) {
        val editor = context.getSharedPreferences(
            context.getString(com.example.scooby.R.string.app_name),
            Context.MODE_PRIVATE
        ).edit()
        editor.clear()
        editor.apply()
    }
}