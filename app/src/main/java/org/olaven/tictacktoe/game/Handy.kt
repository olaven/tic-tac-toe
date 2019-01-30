package org.olaven.tictacktoe.game

// TODO: RENAME FILE!

enum class  SquareMark {
    CROSS, CIRCLE, EMPTY
}

class Square(var mark: SquareMark)
class Move(val mark: SquareMark, val board: Board, val row: Int, val column: Int)
