package org.olaven.tictacktoe.gui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_base.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.database.UserModel
import org.olaven.tictacktoe.gui.fragments.GameFragment
import org.olaven.tictacktoe.gui.fragments.LeaderboardFragment
import org.olaven.tictacktoe.gui.fragments.StartFragment

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        applyTheme()
        replaceMainFragment(StartFragment())
        setupSharedDataListener()
        setContentView(R.layout.activity_base)
        savedInstanceState?.let { restoreFragment(it) }
    }

    private fun restoreFragment(savedInstanceState: Bundle) {

        val key = getString(R.string.fragment_state_key)
        val fragmentName = savedInstanceState.getString(key)

        if (fragmentName == GameFragment().toString())
            replaceMainFragment(GameFragment())
        else
            replaceMainFragment(StartFragment()) //NOTE: Swapped for testing
    }




    override fun onSaveInstanceState(outState: Bundle?) {

        val fragmentName = supportFragmentManager.findFragmentById(R.id.activity_base_frame_layout)
        val key = getString(R.string.fragment_state_key)
        outState?.putString(key, fragmentName.toString())
        super.onSaveInstanceState(outState)
    }


    //TODO: Find out if I should use this or not (used in fragments only, should it go through activity?)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item?.itemId) {

            R.id.menu_main_show_leaderboard -> {
                showLeaderBoard()
                return true
            }
            R.id.menu_main_theme_cabin -> {
                changeTheme(getString(R.string.cabin_theme))
                return true
            }
            R.id.menu_main_theme_default -> {
                changeTheme(getString(R.string.default_theme))
                return true
            }
            R.id.menu_main_theme_dark -> {
                changeTheme(getString(R.string.dark_theme))
                return true
            }
            R.id.menu_main_theme_fruit -> {
                changeTheme(getString(R.string.fruit_theme))
                return true
            }
            R.id.menu_main_admin_clear_users -> {
                clearAllUsers()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearAllUsers() {
        AlertDialog.Builder(this).apply {

            setTitle("Do you want to permanently delete all users?")

            setPositiveButton("Yes, I am sure") { _, _ ->
                UserModel(application).deleteAll()
            }

            setNegativeButton("Cancel") { _, _ -> }
        }.show()
    }

    private fun showLeaderBoard() {
        replaceMainFragment(LeaderboardFragment()) 
    }

    private fun changeTheme(themeName: String) {

        setThemePreference(themeName)

        Snackbar.make(activity_base_frame_layout, "Restart for changes to take effect", Snackbar.LENGTH_LONG).apply {
            this.setAction("restart") {
                restart()
            }
            show()
        }
    }


    private fun setThemePreference(name: String) {

        val editor = getSharedPreferences(getString(R.string.theme_preference), Context.MODE_PRIVATE).edit()

        editor.putString(getString(R.string.theme_key), name)
        editor.apply()
    }


    private fun applyTheme() {

        val preferences = getSharedPreferences(getString(R.string.theme_preference), Context.MODE_PRIVATE);
        val key = getString(R.string.theme_key)


        when(preferences.getString(key, getString(R.string.default_theme))) {
            getString(R.string.default_theme) -> setTheme(R.style.DefaultTheme)
            getString(R.string.dark_theme)    -> setTheme(R.style.DarkTheme)
            getString(R.string.fruit_theme)   -> setTheme(R.style.FruitTheme)
            getString(R.string.cabin_theme)   -> setTheme(R.style.CabinTheme)
        }
    }

    private fun restart() {


        Handler().post {
            val intent = intent
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_NO_ANIMATION
            )

            finish()
            startActivity(intent)
        }
    }
}