package org.olaven.tictacktoe.game.player

import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark
import org.olaven.tictacktoe.positionToCoordinates

class BotPlayer: Player("TTTBot") {

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
}