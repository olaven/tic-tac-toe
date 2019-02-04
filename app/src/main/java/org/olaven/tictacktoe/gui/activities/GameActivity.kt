package org.olaven.tictacktoe.gui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.game.Board
import org.olaven.tictacktoe.game.SquareMark

class GameActivity : AppCompatActivity() {

    val board = Board()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }


    // TODO: something like this should perhaps run on different thread
    private fun updateBoard() {

        board.grid.forEachIndexed { index, square ->
            val value = when (square.mark) {
                SquareMark.EMPTY -> " "
                SquareMark.CIRCLE -> "O"
                SquareMark.CROSS -> "X"
            }


        }
    }

}
