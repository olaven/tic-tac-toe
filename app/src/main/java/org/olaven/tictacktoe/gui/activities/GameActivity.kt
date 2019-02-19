package org.olaven.tictacktoe.gui.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
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

        setupBoard()
        setupText()

        game.onFirstPlayer = {
            Toast.makeText(applicationContext, "first player", Toast.LENGTH_SHORT)
        }
        game.onSecondPlayer = {
            Toast.makeText(applicationContext, "second player", Toast.LENGTH_SHORT)
        }
    }

    // TODO: store the current game-object to preserve through breaks?
    // also, check that pause is correct lifecycle method to do such thing
    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupBoard() {

        activity_game_grid_view.adapter = GameGridAdapter(this, game)
        activity_game_grid_view.numColumns = game.board.dimension
    }


    private fun setupText() {

        activity_game_text_player1.text = game.player1.name
        activity_game_text_player2.text = game.player2.name
    }
}



