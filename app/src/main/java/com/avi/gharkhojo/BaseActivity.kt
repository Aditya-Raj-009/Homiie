package com.avi.gharkhojo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveLastUsedActivity()
    }

    private fun saveLastUsedActivity() {
        val sharedPref = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val className = javaClass.name
        with(sharedPref.edit()) {
            putString("lastUsedActivity", className)
            apply()
        }

    }
}
