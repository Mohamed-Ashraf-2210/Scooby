package com.example.scooby.authentication

import android.content.Context
import android.content.SharedPreferences
import com.example.scooby.R

object TokenManager {
    private const val USER_TOKEN_KEY = "user_token"

    // region save auth token
    fun saveAuthToken(context: Context, token: String) {
        saveString(context, token)
    }

    private fun saveString(context: Context, value: String) {
        val prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(this.USER_TOKEN_KEY, value)
        editor.apply()
    }
    // endregion

    // region fetch auth token
    fun getToken(context: Context): String? {
        return getString(context)
    }

    private fun getString(context: Context): String? {
        val prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(this.USER_TOKEN_KEY, null)
    }
    // endregion

    fun clearToken(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}