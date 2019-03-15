package org.olaven.tictacktoe.gui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.gui.SharedModel
import org.olaven.tictacktoe.gui.fragments.GameFragment
import org.olaven.tictacktoe.gui.fragments.StartFragment

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        applyTheme()
        replaceMainFragment(StartFragment())
        setupSharedDataListener()
        setContentView(R.layout.activity_base)

    }


    override fun onSaveInstanceState(outState: Bundle?) {

        super.onSaveInstanceState(outState)
        val key = getString(R.string.fragment_state_key)
        outState?.putString(key, getString(R.string.fragment_state))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {

        super.onRestoreInstanceState(savedInstanceState)
        val key = getString(R.string.fragment_state_key)
        val state = savedInstanceState?.getString(key)

        if (state.equals("game")) {

            replaceMainFragment(GameFragment())
        } else {
            replaceMainFragment(StartFragment())
        }
    }

    private fun setupSharedDataListener() {

        val sharedViewModel = ViewModelProviders.of(this).get(SharedModel::class.java)

        sharedViewModel.player1Name.observe(this, Observer {
            it?.let {
                // do some thing with the number
            }
        })

        sharedViewModel.player2Name.observe(this, Observer {
            it?.let {

            }
        })
    }


    fun replaceMainFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_base_frame_layout, fragment)
        transaction.commit()
    }

    /*
    override fun onStart() {

        // restoreSpinners()
        super.onStart()
    }

    override fun onStop() {

        Toast.makeText(applicationContext, "stopper", Toast.LENGTH_SHORT).show()
        val editor = getPreferences(Context.MODE_PRIVATE).edit()

        editor
            .putInt(getString(R.string.player1_position_key), activity_start_spinner_player1.selectedItemPosition)
            .apply()
        editor
            .putInt(getString(R.string.player2_position_key), activity_start_spinner_player2.selectedItemPosition)
            .apply()
        super.onStop()
    }

    private fun restoreSpinners() {

        Toast.makeText(applicationContext, "Restorings pinners", Toast.LENGTH_SHORT).show()
        val preferences = getPreferences(Context.MODE_PRIVATE)

        val firstPosition = preferences.getInt(getString(R.string.player1_position_key), 0)
        val secondPosition= preferences.getInt(getString(R.string.player2_position_key), 0)

        activity_start_spinner_player1.setSelection(firstPosition)
        activity_start_spinner_player2.setSelection(secondPosition)
    }

    protected fun setThemePreference(name: String) {

        val editor = getSharedPreferences(getString(R.string.theme_preference), Context.MODE_PRIVATE).edit()

        editor.putString(getString(R.string.theme_key), name)
        editor.apply()
    }
    */

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