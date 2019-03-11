package org.olaven.tictacktoe.game

import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark

fun hasWinner(board: Board): Boolean =
        verticalWinner(board) || horizontalWinner(board) || diagonalWinner(board)


private fun verticalWinner(board: Board): Boolean =
        straightWinner(Direction.VERTICAL, board)


private fun horizontalWinner(board: Board): Boolean =
        straightWinner(Direction.HORIZONTAL, board)

private fun diagonalWinner(board: Board): Boolean =
        false


private fun straightWinner(direction: Direction, board: Board): Boolean {
    for(i in 0 until board.dimension) {

        var previous: SquareMark? = null
        for(j in 0 until board.dimension) {

            val coordinate= when (direction) {
                Direction.HORIZONTAL -> Coordinate(i, j)
                Direction.VERTICAL -> Coordinate(j, i)
            }

            val current = board.squareAt(coordinate).mark
            if (previous == null) previous = current

            if (previous != current) {
                continue
            }

            if (j == board.dimension - 1) {
                return true
            }
        }
    }

    return false
}

private enum class Direction {
    HORIZONTAL, VERTICAL
}