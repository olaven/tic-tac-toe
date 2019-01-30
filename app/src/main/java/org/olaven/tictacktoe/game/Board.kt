package org.olaven.tictacktoe.game

class Board(dimension: Int = 3) {

    val grid = Grid(rows = dimension, columns = dimension, defaultContent = Square(SquareMark.EMPTY))

    fun squareAt(row: Int, column: Int): Square {

        return grid.matrix[row][column];
    }

    fun markSquareAt(row: Int, column: Int, newMark: SquareMark) {
        val square = squareAt(row, column)
        square.mark = newMark
    }

}
