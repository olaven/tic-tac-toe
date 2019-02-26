package org.olaven.tictacktoe

import org.olaven.tictacktoe.game.CanCopy
import org.olaven.tictacktoe.game.board.Coordinate
import org.olaven.tictacktoe.game.board.Grid

fun<T: CanCopy<T>> positionToCoordinates (position: Int, grid: Grid<T>): Coordinate {
    val row = position / grid.rows
    val column = position % grid.columns

    return Coordinate(row, column)
}