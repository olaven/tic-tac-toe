package org.olaven.tictacktoe.game

data class Square(var mark: SquareMark): CanCopy<Square> {
    override fun copy(): Square {
        return Square(mark)
    }
}