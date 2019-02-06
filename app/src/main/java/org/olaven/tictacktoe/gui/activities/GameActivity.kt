package org.olaven.tictacktoe.gui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.game.Board
import org.olaven.tictacktoe.game.Game
import org.olaven.tictacktoe.game.player.HumanPlayer
import org.olaven.tictacktoe.gui.adapters.GameGridAdapter


class GameActivity : AppCompatActivity() {

    private val board = Board()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        activity_game_grid_view.adapter = GameGridAdapter(this, Game(Board(), HumanPlayer("player one"), HumanPlayer("player 2")))
    }


}



