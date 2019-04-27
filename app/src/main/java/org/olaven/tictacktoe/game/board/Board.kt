package org.olaven.tictacktoe.game.board

import org.olaven.tictacktoe.game.CanCopy
import org.olaven.tictacktoe.positionToCoordinates

class Board(val dimension: Int = 3): CanCopy<Board> {

    var moveCount = 0

    val grid = Grid(
        rows = dimension,
        columns = dimension,
        defaultContent = Square(SquareMark.EMPTY)
    )
    val size: Int
        get() = dimension * dimension

    fun squareAt(coordinate: Coordinate): Square {

        return grid.matrix[coordinate.x][coordinate.y]
    }

    fun markSquareAt(coordinate: Coordinate, newMark: SquareMark) {

        moveCount++
        val square = squareAt(coordinate)
        square.mark = newMark
    }


    override fun copy(): Board {
        val board = Board(this.dimension)
        board.grid.mapIndexed { index, square ->
            val coordinate = positionToCoordinates(index, board.grid)
            val mark = this.squareAt(coordinate).mark
            board.markSquareAt(coordinate, mark)
        }
        return board
    }
}
