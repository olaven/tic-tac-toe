package org.olaven.tictacktoe.gui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start.*
import org.olaven.tictacktoe.R

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        activity_main_button_start.setOnClickListener {

            val user1 = "Olav" // PLACEHOLDERS!
            val user2 = "Guro"

            val intent = Intent(applicationContext, GameActivity::class.java).apply {
                putExtra("user1", user1)
                putExtra("user2", user2)
            }

            startActivity(intent)

        }
    }

    // USE LIFECYLCLE METHODS FOR ALL THEY ARE WORTH!
}
