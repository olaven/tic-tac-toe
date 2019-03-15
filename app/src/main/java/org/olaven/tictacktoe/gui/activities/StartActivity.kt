package org.olaven.tictacktoe.gui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*
import org.olaven.tictacktoe.R

/*
class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState) // has to be called first for themes to work
        setContentView(R.layout.activity_start)

        setupSpinners()
        setupButton()
    }

    override fun onStart() {

        restoreSpinners()
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

    private fun setupSpinners() {

        val humanPlayers = resources.getStringArray(R.array.humanPlayers)
        val aiPlayers = resources.getStringArray(R.array.aiPlayers)

        activity_start_spinner_player1.adapter = getAdapter(humanPlayers)
        activity_start_spinner_player2.adapter = getAdapter(humanPlayers + aiPlayers)
    }

    private fun setupButton() {
        activity_start_button_start.setOnClickListener {

            val player1 = activity_start_spinner_player1.selectedItem.toString()
            val player2 = activity_start_spinner_player2.selectedItem.toString()

            if (player1 == player2) {
                activity_start_spinner_player2.performClick()
                Toast.makeText(applicationContext, getString(R.string.same_user_message), Toast.LENGTH_SHORT).show()
            } else {
                val gameIntent = Intent(applicationContext, GameActivity::class.java).apply {
                    putExtra(getString(R.string.player1_key), player1)
                    putExtra(getString(R.string.player2_key), player2)
                }

                startActivity(gameIntent)
            }
        }
    }


    private fun restoreSpinners() {

        Toast.makeText(applicationContext, "Restorings pinners", Toast.LENGTH_SHORT).show()
        val preferences = getPreferences(Context.MODE_PRIVATE)

        val firstPosition = preferences.getInt(getString(R.string.player1_position_key), 0)
        val secondPosition= preferences.getInt(getString(R.string.player2_position_key), 0)

        activity_start_spinner_player1.setSelection(firstPosition)
        activity_start_spinner_player2.setSelection(secondPosition)
    }



    private fun getAdapter(array: Array<String>) =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, array)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        item?.let {
            return when(it.itemId) {
                R.id.menu_main_add_user -> showAddUserMenu()
                R.id.menu_theme_dark -> showChangeThemeMenu()
                else -> false
            }
        }
        return false
    }

    private fun showChangeThemeMenu(): Boolean {

        PopupMenu(applicationContext, activity_start).apply {

            setOnMenuItemClickListener {
                val themeName = it.title.toString()
                changeTheme(themeName)
                true
            }
            inflate(R.menu.menu_theme)
            gravity = Gravity.CENTER
            show()
        }


        return true
    }

    private fun changeTheme(themeName: String) {

        setThemePreference(themeName)

        Snackbar.make(activity_start, "Restart for changes to take effect", Snackbar.LENGTH_LONG).apply {
            this.setAction("restart") {
                restart()
            }
            show()
        }
    }

    private fun restart() {
        val intent = Intent(applicationContext, StartActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    private fun showAddUserMenu(): Boolean {
        Toast.makeText(applicationContext, "I want to add user", Toast.LENGTH_SHORT).show()
        return true
    }

}
*/