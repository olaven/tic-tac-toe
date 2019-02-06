package org.olaven.tictacktoe.game

// TODO: RENAME FILE!

enum class  SquareMark {
    CROSS, CIRCLE, EMPTY
}

class Square(var mark: SquareMark)
class Move(val mark: SquareMark, val board: Board, val coordinate: Coordinate)
data class Coordinate(val x: Int, val y: Int)
