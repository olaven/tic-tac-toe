package org.olaven.tictacktoe.gui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*
import org.olaven.tictacktoe.R

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        setupSpinners()
        setupButton()

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
                    putExtra("player1", player1)
                    putExtra("player2", player2)
                }

                startActivity(gameIntent)
            }
        }
    }


    private fun getAdapter(array: Array<String>) =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, array)

}
