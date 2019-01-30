package org.olaven.tictacktoe.game

class Grid<T>(rows: Int, columns: Int, defaultContent: T) {

    val matrix = List(rows) {
        List(columns) {
            defaultContent
        }
    }
    val size: Int = rows * columns

}