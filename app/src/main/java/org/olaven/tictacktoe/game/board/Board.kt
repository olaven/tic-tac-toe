package org.olaven.tictacktoe.game.board

class Board(val dimension: Int = 3) {

    val grid = Grid(
        rows = dimension,
        columns = dimension,
        defaultContent = Square(SquareMark.EMPTY)
    )

    fun squareAt(coordinate: Coordinate): Square {

        return grid.matrix[coordinate.x][coordinate.y]
    }


    fun markSquareAt(coordinate: Coordinate, newMark: SquareMark) {

        val square = squareAt(coordinate)
        square.mark = newMark
    }
}
