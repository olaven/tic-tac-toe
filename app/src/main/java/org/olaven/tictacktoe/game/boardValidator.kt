package org.olaven.tictacktoe.game

import org.olaven.tictacktoe.game.board.Board
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.SquareMark


/*
* Passing the latest move as well. That allows me to limit the search space
* */
fun hasWinner(latestMove: Coordinate, board: Board): Boolean =
        verticalWinner(latestMove, board) || horizontalWinner(latestMove, board) || diagonalWinnerLeftDown(board) || diagonalWinnerLeftUp(board)


private fun verticalWinner(coordinate: Coordinate, board: Board) =
    straightWinner(Direction.VERTICAL, coordinate, board)


private fun horizontalWinner(coordinate: Coordinate, board: Board) =
    straightWinner(Direction.HORIZONTAL, coordinate, board)

private fun diagonalWinnerLeftDown(board: Board): Boolean {

    for(i in 1 until board.dimension) {

        val previous = board.squareAt(Coordinate(i - 1, i - 1)).mark
        val current = board.squareAt(Coordinate(i, i)).mark

        if (previous == SquareMark.EMPTY || current == SquareMark.EMPTY)
            return false
        if (current != previous)
            return false
    }

    return true
}

private fun diagonalWinnerLeftUp(board: Board): Boolean {

    val limit = board.dimension - 1
    for(x in 1 until board.dimension) {

        val y = limit - x

        val previous = board.squareAt(Coordinate(x - 1, y + 1)).mark
        val current = board.squareAt(Coordinate(x, y)).mark

        if (previous == SquareMark.EMPTY || current == SquareMark.EMPTY)
            return false
        if (current != previous)
            return false
    }

    return true
}


private fun straightWinner(direction: Direction, latestMove: Coordinate, board: Board): Boolean {

    for(i in 1 until board.dimension) {

        val currentCoordinate = when(direction) {
            Direction.HORIZONTAL -> Coordinate(i, latestMove.y)
            Direction.VERTICAL -> Coordinate(latestMove.x, i)
        }

        val previousCoordinate = when(direction) {
            Direction.HORIZONTAL -> Coordinate(i - 1, latestMove.y)
            Direction.VERTICAL -> Coordinate(latestMove.x, i - 1)
        }

        val current = board.squareAt(currentCoordinate).mark
        val previous = board.squareAt(previousCoordinate).mark



        if (previous == SquareMark.EMPTY || current == SquareMark.EMPTY)
            return false
        if (current != previous)
            return false
    }
    return true
}

private enum class Direction {
    HORIZONTAL, VERTICAL
}