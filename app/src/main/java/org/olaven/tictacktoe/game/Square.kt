package org.olaven.tictacktoe.game

enum class  SquareState {
    CROSS, CIRCLE, EMPTY
}

class Square(var state: SquareState)