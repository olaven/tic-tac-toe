package org.olaven.tictacktoe.game.player

import org.olaven.tictacktoe.database.User
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark
import org.olaven.tictacktoe.game.hasWinner
import org.olaven.tictacktoe.positionToCoordinates
import java.lang.IllegalStateException

class BotPlayer(user: User): Player(user) {

    fun selectCoordinate(board: Board): Coordinate {
        // TODO: Run searching on different thread!
        // TODO: Find the next best move based on the currentBoard and make it.
        // Finding of move on different thread.
        // making the move with inherited makemove(move) has to be done on UI Thread again

        findCriticalPoint(board)?.let {
            return it
        }

        //just pick one at random
        board.grid.forEachIndexed { index, square ->
            if (square.mark == SquareMark.EMPTY) {
                return positionToCoordinates(index, board.grid)
            }
        }

        throw IllegalStateException("Searching for a move, but board is full and game is over.")
    }

    //NOTE: a critical point is a point where all are equal except an empty square, meaning next move is eather win or loss
    // Go through all points, find one where game _would be_ over, and make that move 
    private fun findCriticalPoint(currentBoard: Board): Coordinate? {



        currentBoard.grid.forEachIndexed { index, square ->

            // copy so that test do not affect game
            val board = currentBoard.copy()

            val coordinate = positionToCoordinates(index, board.grid)
            if (board.squareAt(coordinate).mark == SquareMark.EMPTY)
                if (isWin(board, coordinate, SquareMark.CIRCLE))
                    return coordinate
                if (isWin(board, coordinate, SquareMark.CROSS))
                    return coordinate

        }
        return null
    }

    private fun isWin(board: Board, coordinate: Coordinate, mark: SquareMark): Boolean {

        board.markSquareAt(coordinate, mark)
        return hasWinner(coordinate, board)
    }

}