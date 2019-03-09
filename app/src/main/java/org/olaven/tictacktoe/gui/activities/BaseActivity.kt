package org.olaven.tictacktoe.gui.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.olaven.tictacktoe.R

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        applyTheme()
        super.onCreate(savedInstanceState)
    }



    private fun applyTheme() {

        val preferences = getSharedPreferences(getString(R.string.theme_preference), Context.MODE_PRIVATE);
        val key = getString(R.string.theme_key)

        val present = preferences.contains(key)
        if (present) {
            when(preferences.getString(key, "default")) {
                getString(R.string.default_theme) -> setTheme(R.style.DefaultTheme)
                getString(R.string.dark_theme)    -> setTheme(R.style.DarkTheme)
                getString(R.string.fruit_theme)   -> setTheme(R.style.FruitTheme)
                getString(R.string.cabin_theme)   -> setTheme(R.style.CabinTheme)
            }
        }
    }
}