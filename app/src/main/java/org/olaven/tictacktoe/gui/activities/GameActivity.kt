package org.olaven.tictacktoe.gui.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.Game
import org.olaven.tictacktoe.game.player.BotPlayer
import org.olaven.tictacktoe.game.player.HumanPlayer
import org.olaven.tictacktoe.gui.adapters.GameGridAdapter


class GameActivity : AppCompatActivity() {

    var game: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        setupGame()
        setupBoardView()
        setupText()
        setupHighlight()
    }

    // TODO: store the current game-object to preserve through breaks?
    // also, check that pause is correct lifecycle method to do such thing
    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupGame() {

        val player1Name = intent.extras["player1"] as String
        val player2Name = intent.extras["player2"] as String

        val player1 = HumanPlayer(player1Name)
        val player2 = if (player2Name.startsWith("AI")) {
            BotPlayer()
        } else {
            HumanPlayer(player2Name)
        }


        game = Game(Board(), player1, player2)
    }

    private fun setupBoardView() {

        game?.let {
            activity_game_grid_view.adapter = GameGridAdapter(this, it)
            activity_game_grid_view.numColumns = it.board.dimension
        }
    }


    private fun setupText() {

        game?.let {
            activity_game_text_player1.text = it.player1.name
            activity_game_text_player2.text = it.player2.name
        }
    }

    private fun setupHighlight() {

        game?.let {
            it.onFirstPlayer = { player ->
                activity_game_text_player1.setTextColor(Color.GREEN)
                activity_game_text_player2.setTextColor(Color.BLACK)
            }

            it.onSecondPlayer = { player ->
                activity_game_text_player1.setTextColor(Color.BLACK)
                activity_game_text_player2.setTextColor(Color.GREEN)

                if (player is BotPlayer) {
                    player.makeMove()
                }
            }
        }

    }
}



