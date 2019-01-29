package org.olaven.tictacktoe.game

class Board(rows: Int = 3, columns: Int= 3) {

    val grid = Array(rows) {
        ArrayList<Square>()
    }

    init {
        // initialize grid
        for (i in 0 until rows) {
            for(j in 0 until columns) {
                grid[i].add(
                    Square(SquareState.EMPTY)
                )
            }
        }
    }

    fun squareAt(row: Int, column: Int): Square {

        return grid[row][column]
    }

    fun markSquareAt(row: Int, column: Int, newState: SquareState) {
        val square = squareAt(row, column)
        square.state = newState
    }

}
