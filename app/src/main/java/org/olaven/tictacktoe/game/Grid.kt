package org.olaven.tictacktoe.game

class Grid<T: CanCopy<T>>(val rows: Int, val columns: Int, defaultContent: T): Iterable<T> {


    val matrix = List(rows) {
        List(columns) {
            defaultContent.copy()
        }
    }
    val size: Int = rows * columns

    override fun iterator(): Iterator<T> {


        return object : Iterator<T> {
            val current = object {
                var row = 0
                var column = 0
            }

            val maxRow = matrix.size - 1
            val maxColumn = matrix[maxRow].size - 1

            override fun hasNext(): Boolean {

                return (current.row <= maxRow && current.column <= maxColumn)
            }

            override fun next(): T {

                var element = matrix[current.row][current.column]

                if (current.column == maxColumn) {
                    current.row += 1
                    current.column = 0
                } else {
                    current.column += 1
                }

                return element

            }
        }
    }
}