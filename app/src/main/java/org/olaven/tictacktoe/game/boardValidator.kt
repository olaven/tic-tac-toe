package org.olaven.tictacktoe.game

import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark
import android.R.attr.y
import android.R.attr.x



fun hasWinner(coordinate: Coordinate, board: Board): Boolean =
        verticalWinner(coordinate, board) || horizontalWinner(coordinate, board) || diagonalWinner(board)


private fun verticalWinner(coordinate: Coordinate, board: Board): Boolean =
        straightWinner(Direction.VERTICAL, coordinate, board)


private fun horizontalWinner(coordinate: Coordinate, board: Board): Boolean =
        straightWinner(Direction.HORIZONTAL, coordinate, board)

private fun diagonalWinner(board: Board): Boolean {

    for(i in 1 until board.dimension) {

        val previous = board.squareAt(Coordinate(i - 1, i - 1)).mark
        val current = board.squareAt(Coordinate(i, i)).mark

        if (previous != current)
            return false

    }

    var y = 1
    for(x in board.dimension downTo 1) {

        val previous = board.squareAt(Coordinate(x, y - 1))
        val current = board.squareAt(Coordinate(x, y))

        if (previous != current)
            return false

        y++
    }

    return true
}


private fun straightWinner(direction: Direction, coordinate: Coordinate, board: Board): Boolean {

    for(i in 1 until board.dimension) {

        val current = board.squareAt(coordinate).mark
        val previous = when (direction) {
            Direction.VERTICAL -> board.squareAt(Coordinate(i, coordinate.y)).mark
            Direction.HORIZONTAL -> board.squareAt(Coordinate(coordinate.x, i)).mark
        }

        if (current != previous)
            return false

    }

    return true
}

private enum class Direction {
    HORIZONTAL, VERTICAL
}