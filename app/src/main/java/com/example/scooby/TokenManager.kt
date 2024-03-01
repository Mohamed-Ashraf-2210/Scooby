package com.example.scooby

import android.content.Context
import com.example.scooby.utils.Constant

object TokenManager {

    // region save auth token
    fun saveAuthToken(context: Context, value: String) {
        val prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(Constant.USER_TOKEN, value)
        editor.apply()
    }
    // endregion

    // region fetch auth token
    fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(Constant.USER_TOKEN, null)
    }
    // endregion

    // region clear auth token
    fun clearToken(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
    // endregion
}