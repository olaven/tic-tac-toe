package org.olaven.tictacktoe.gui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*
import org.olaven.tictacktoe.R

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState) // has to be called first for themes to work
        setContentView(R.layout.activity_start)

        setupSpinners()
        setupButton()
        restoreSpinners(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {

        super.onSaveInstanceState(outState)
        Toast.makeText(applicationContext, "saving state", Toast.LENGTH_SHORT).show()
        outState?.let {
            it.putInt(getString(R.string.player1_position_key), activity_start_spinner_player1.selectedItemPosition)
            it.putInt(getString(R.string.player2_position_key), activity_start_spinner_player2.selectedItemPosition)
        }
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


    private fun restoreSpinners(savedInstanceState: Bundle?) {

        Toast.makeText(applicationContext, "oncreate kjører", Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext, "savedInstanceState: ${savedInstanceState != null}", Toast.LENGTH_SHORT)
            .show()
        savedInstanceState?.let {
            Toast.makeText(applicationContext, "restoring state", Toast.LENGTH_SHORT).show()
            val player1Position = it.getInt(getString(R.string.player1_position_key))
            val player2Position = it.getInt(getString(R.string.player2_position_key))

            activity_start_spinner_player1.setSelection(player1Position)
            activity_start_spinner_player2.setSelection(player2Position)
        }
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
                changeTheme(it)
                true
            }
            inflate(R.menu.menu_theme)
            gravity = Gravity.CENTER
            show()
        }


        return true
    }

    private fun changeTheme(item: MenuItem) {

        val editor = getSharedPreferences(getString(R.string.theme_preference), Context.MODE_PRIVATE).edit()
        val value = item.title.toString()

        editor.putString(getString(R.string.theme_key), value)
        editor.apply()

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
