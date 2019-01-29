package org.olaven.tictacktoe.gui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.olaven.tictacktoe.R

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }
}
