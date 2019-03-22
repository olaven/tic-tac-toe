package org.olaven.tictacktoe.game.player

import android.provider.Settings.Global.getString
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.database.User
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark
import org.olaven.tictacktoe.positionToCoordinates

class BotPlayer(user: User): Player(user) {

    fun selectCoordinate(board: Board): Coordinate {
        // TODO: Run searching on different thread!
        // TODO: Find the next best move based on the board and make it.
        // Finding of move on different thread.
        // making the move with inherited makemove(move) has to be done on UI Thread again


        board.grid.forEachIndexed { index, square ->
            if (square.mark == SquareMark.EMPTY) {
                return positionToCoordinates(index, board.grid)
            }
        }

        return Coordinate(0, 0)
    }

    /*private fun findWin(board: Board): Coordinate {

        for (i in 0 until board.grid.rows){

        }
    }*/


}