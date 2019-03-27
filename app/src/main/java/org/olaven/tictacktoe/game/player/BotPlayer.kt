package org.olaven.tictacktoe.game.player

import org.olaven.tictacktoe.database.User
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark
import org.olaven.tictacktoe.game.hasWinner
import org.olaven.tictacktoe.positionToCoordinates

class BotPlayer(user: User): Player(user) {

    fun selectCoordinate(board: Board): Coordinate {
        // TODO: Run searching on different thread!
        // Finding of move on different thread.
        // making the move with inherited makemove(move) has to be done on UI Thread again

        // find a necessary move
        findCriticalPoint(board)?.let {
            return it
        }

        // Pick random empty
        return board.grid.mapIndexed { index, _ ->
            positionToCoordinates(index, board.grid)
        }.filter {coordinate ->
            board.squareAt(coordinate).mark == SquareMark.EMPTY
        }.random()
    }

    //NOTE: a critical point is a point where all are equal except an empty square, meaning next move is eather win or loss
    // Go through all points, find one where game _would be_ over, and make that move 
    private fun findCriticalPoint(board: Board): Coordinate? {



        board.grid.forEachIndexed { index, square ->

            val coordinate = positionToCoordinates(index, board.grid)
            val mark = square.mark

            if (mark == SquareMark.EMPTY) {

                if (isWin(board, coordinate, SquareMark.CIRCLE))
                    return coordinate
                if (isWin(board, coordinate, SquareMark.CROSS))
                    return coordinate
            }
        }
        return null
    }

    private fun isWin(currentBoard: Board, coordinate: Coordinate, mark: SquareMark): Boolean {

        // copy so that test do not affect game
        val board = currentBoard.copy()
        
        board.markSquareAt(coordinate, mark)
        return hasWinner(coordinate, board)
    }

}