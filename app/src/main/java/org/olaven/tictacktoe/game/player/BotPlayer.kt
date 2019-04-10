package org.olaven.tictacktoe.game.player

import org.olaven.tictacktoe.database.User
import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark
import org.olaven.tictacktoe.game.hasWinner
import org.olaven.tictacktoe.positionToCoordinates
import java.lang.Exception

class BotPlayer(user: User): Player(user) {

    fun selectCoordinate(board: Board): Coordinate {

        // find a necessary move
        findCriticalPoint(board)?.let {

            return it
        }

        findLongestBuildingPoint(board)?.let {

            return it
        }

        findRandomPoint(board).let {

            return it
        }
    }

    private fun findLongestBuildingPoint(board: Board): Coordinate? {

        board.grid.forEachIndexed {index, square ->

            val coordinate = positionToCoordinates(index, board.grid)
            if (square.mark == SquareMark.EMPTY) {

                val neighbourIsMine = neighbourBelongsToAI(coordinate, board)
                val spoiled = spoiledRow(coordinate, board)

                if (!spoiled) {
                    println("Got here");
                }

                if (neighbourIsMine && !spoiled) {
                    return coordinate
                }
            }
        }
        return null
    }


    private fun neighbourBelongsToAI(coordinate: Coordinate, board: Board): Boolean {

        listOf(
            Coordinate(coordinate.x + 1, coordinate.y),
            Coordinate(coordinate.x - 1, coordinate.y),
            Coordinate(coordinate.x, coordinate.y - 1),
            Coordinate(coordinate.x, coordinate.y + 1)
        ).forEach { neighbour ->

            try {
                if (board.squareAt(neighbour).mark == SquareMark.CIRCLE )
                    return true
            } catch (exception: Exception) {
                // intentionally blank, as it just means we are checking something on
                // a border. In that case, we just want to go on.
            }
        }

        return false
    }

    // the coordinate is on a spoiled row, there is
    // no way the game can be won on it
    private fun spoiledRow(coordinate: Coordinate, board: Board): Boolean {

        val x = coordinate.x
        val y = coordinate.y

        for(i in 0 until board.dimension) {

            val horizontal = board.squareAt(Coordinate(x, i)).mark
            val vertical = board.squareAt(Coordinate(i, y)).mark
            val diagonal = board.squareAt(Coordinate(x, y)).mark //TODO: Check diagonal

            if (horizontal == SquareMark.CROSS && vertical == SquareMark.CROSS) {
                return true 
            }
        }
        return false
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

    private fun findRandomPoint(board: Board) = board.grid.mapIndexed { index, _ ->
            positionToCoordinates(index, board.grid)
        }.filter { coordinate ->
            board.squareAt(coordinate).mark == SquareMark.EMPTY
        }.random()

    private fun isWin(currentBoard: Board, coordinate: Coordinate, mark: SquareMark): Boolean {

        // copy so that test do not affect game
        val board = currentBoard.copy()
        
        board.markSquareAt(coordinate, mark)
        return hasWinner(coordinate, board)
    }

}