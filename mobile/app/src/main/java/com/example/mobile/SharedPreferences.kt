package com.example.mobile

import android.content.Context

class SharedPreferences {
    val PREFS_NAME = "my_prefs"

    fun save(key: String, value: String, context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
    fun get(key: String, context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }
}
