package org.olaven.tictacktoe.gui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.Game
import org.olaven.tictacktoe.game.player.HumanPlayer
import org.olaven.tictacktoe.gui.adapters.GameGridAdapter


class GameActivity : AppCompatActivity() {

    private val game = Game(Board(), HumanPlayer("player 1"), HumanPlayer("player 2"))

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val adapter = GameGridAdapter(this, game)
        activity_game_grid_view.numColumns = game.board.dimension
        activity_game_grid_view.adapter = adapter
    }

    // TODO: store the current game-object to preserve through breaks?
    // also, check that pause is correct lifecycle method to do such thing
    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }
}



